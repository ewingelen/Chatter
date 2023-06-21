package com.ewingelen.chatter.chats.main.data.cloud

import com.ewingelen.chatter.core.data.cloud.ProvideUserReference
import com.ewingelen.chatter.core.data.cloud.model.ChatCloud
import com.ewingelen.chatter.core.data.cloud.model.ProvideUserId
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ChatsCloudDataSource {

    fun fetchChats(): Flow<List<ChatCloud>>

    fun deleteChat(id: String)

    class Base @Inject constructor(
        private val provideUserId: ProvideUserId,
        private val provideUserReference: ProvideUserReference.Document
    ) : ChatsCloudDataSource {

        private val chatsCollections = Firebase.firestore.collection("chats")

        override fun fetchChats() = callbackFlow {
            val snapshotListener = chatsCollections.whereArrayContains(
                "membersId", provideUserId.provide()
            ).addSnapshotListener { snapshot, e ->
                val chats = snapshot?.toObjects(ChatCloud::class.java) ?: emptyList()
                launch { send(chats) }
            }
            awaitClose { snapshotListener.remove() }
        }

        override fun deleteChat(id: String) {
            chatsCollections.document(id).delete()
//            val user = provideUserReference.document().update(
//                "savedContacts"
//            )
        }
    }
}