package com.ewingelen.chatter.chats.create.domain

import com.ewingelen.chatter.core.domain.HandleDomainError
import com.ewingelen.chatter.core.domain.model.Chat
import javax.inject.Inject
import javax.inject.Named

interface CreateChatInteractor {

    suspend fun createChat(chat: Chat): CreateChatResult

    class Base @Inject constructor(
        private val repository: CreateChatRepository,
        @Named("CreateChat")
        private val handleError: HandleDomainError<String>
    ) : CreateChatInteractor {

        override suspend fun createChat(chat: Chat) =
            try {
                repository.createChat(chat)
                CreateChatResult.Success()
            } catch (e: Exception) {
                val error = handleError.handle(e)
                CreateChatResult.Fail(error)
            }
    }
}