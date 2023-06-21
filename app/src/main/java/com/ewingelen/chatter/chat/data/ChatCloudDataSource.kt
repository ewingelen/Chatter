package com.ewingelen.chatter.chat.data

import android.net.Uri
import com.ewingelen.chatter.core.data.cloud.model.ChatCloud
import com.ewingelen.chatter.core.data.cloud.model.MessageCloud
import com.ewingelen.chatter.core.domain.ProvideResources
import com.ewingelen.chatter.core.domain.model.AttachedFile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

interface ChatCloudDataSource {

    suspend fun chat(id: String): Flow<ChatCloud>

    fun sendMessage(chatId: String, message: MessageCloud)

    suspend fun sendFiles(chatId: String, files: List<Uri>)

    fun deleteMessage(chatId: String, message: MessageCloud)

    suspend fun editMessage(chatId: String, newText: String, position: Int)

    abstract class Abstract : ChatCloudDataSource {

        private val chatsCollection = Firebase.firestore.collection("chats")

        protected fun chatDocument(chatId: String) = chatsCollection.document(chatId)

        protected fun addMessage(chatDocument: DocumentReference, message: MessageCloud) {
            chatDocument.update("messages", FieldValue.arrayUnion(message))
        }
    }

    class Base @Inject constructor(
        private val provideFileNameByUri: ProvideFileNameByUri,
        private val provideResources: ProvideResources
    ) : Abstract() {

        override suspend fun chat(id: String) = callbackFlow {
            val snapshotListener = chatDocument(id).addSnapshotListener { snapshot, e ->
                val chat = snapshot!!.toObject(ChatCloud::class.java)!!
                launch { send(chat) }
            }
            awaitClose { snapshotListener.remove() }
        }

        override fun sendMessage(chatId: String, message: MessageCloud) {
            addMessage(chatDocument(chatId), message)
        }

        override suspend fun sendFiles(chatId: String, files: List<Uri>) {
            files.forEach { file ->
                val fileName = provideFileNameByUri.provide(file)
                val uploadTask = Firebase.storage.getReference("$SENT_FILES_LOCATION/$fileName")
                    .putFile(file).await()
                val fileMetadata = uploadTask.metadata!!
                val downloadUrl = fileMetadata.reference!!.downloadUrl.await()
                val fileSize = String.format("%.2f", fileMetadata.sizeBytes.toFloat() / 1000000)
                val attachedFile = AttachedFile(
                    name = fileName,
                    downloadUrl = downloadUrl.toString(),
                    type = fileMetadata.contentType!!,
                    size = fileSize
                )
                addMessage(
                    chatDocument(chatId),
                    MessageCloud(
                        id = UUID.randomUUID().toString(),
                        senderId = Firebase.auth.uid!!,
                        file = attachedFile
                    )
                )
            }
        }

        override fun deleteMessage(chatId: String, message: MessageCloud) {
            chatDocument(chatId).update("messages", FieldValue.arrayRemove(message))
        }

        override suspend fun editMessage(chatId: String, newText: String, position: Int) {
            val chatDocument = chatDocument(chatId)
            val messages = chatDocument.get().await().get("messages") as ArrayList<*>
            val element = messages[position] as? HashMap<String, Any>
            element?.put("text", newText)
            chatDocument.update("messages", messages)
        }
    }

    private companion object {
        const val SENT_FILES_LOCATION = "sent_files/"
    }
}