package com.ewingelen.chatter.createChat.domain

import com.ewingelen.chatter.core.domain.model.Chat
import javax.inject.Inject


interface CreateChatInteractor {

    suspend fun createChat(
        chat: Chat,
        onSuccess: () -> Unit,
        onFail: (String) -> Unit
    )

    //TODO: try "CreateChatResult"
    //TODO: onSuccess and onResult to interface
    class Base @Inject constructor(
        private val repository: CreateChatRepository,
//        private val handleError: HandleError<String>,
    ) : CreateChatInteractor {

        override suspend fun createChat(
            chat: Chat,
            onSuccess: () -> Unit,
            onFail: (String) -> Unit
        ) {
            try {
                repository.createChat(chat)
                onSuccess()
            } catch (e: Exception) {
//                val error = handleError.handle(e)
//                onFail(error)
            }
        }
    }
}