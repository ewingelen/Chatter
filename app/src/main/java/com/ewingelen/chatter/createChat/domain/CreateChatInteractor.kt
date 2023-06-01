package com.ewingelen.chatter.createChat.domain

import com.ewingelen.chatter.core.domain.HandleError
import com.ewingelen.chatter.core.domain.model.Chat
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
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
        private val handleError: HandleError,
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
                val error = handleError.handle(e)
                onFail(error)
            }
        }
    }
}