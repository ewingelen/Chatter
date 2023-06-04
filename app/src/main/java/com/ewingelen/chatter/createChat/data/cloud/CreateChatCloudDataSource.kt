package com.ewingelen.chatter.createChat.data.cloud

import com.ewingelen.chatter.core.data.cloud.ProvideUserReference
import com.ewingelen.chatter.core.data.cloud.model.ChatCloud
import com.ewingelen.chatter.core.data.cloud.model.UserCloud
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


interface CreateChatCloudDataSource {

    suspend fun createChat(chat: ChatCloud): String

    class Base @Inject constructor(
        private val errorMapper: CreateChatErrorMapper,
        private val provideUserReference: ProvideUserReference.Collection
    ) : CreateChatCloudDataSource {

        //TODO: understand cancelable
        //TODO: check
        override suspend fun createChat(chat: ChatCloud) =
            suspendCancellableCoroutine { continuation ->
                provideUserReference.collection().whereEqualTo(PHONE_NUMBER_FIELD_PATH, chat)
                    .addSnapshotListener { snapshot, e ->
                        e?.let {
                            val error = errorMapper.map(e)
                            continuation.resumeWithException(error)
                        }
                        if (snapshot?.isEmpty == true) {
                            continuation.resumeWithException(UserNotFoundException())
                        } else {
                            val user = snapshot?.first()?.toObject(UserCloud::class.java)
                            continuation.resume(user!!.id)
                        }
                    }
            }

        private companion object {
            const val PHONE_NUMBER_FIELD_PATH = "phone_number"
        }
    }
}