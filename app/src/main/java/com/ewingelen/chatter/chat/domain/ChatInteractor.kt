package com.ewingelen.chatter.chat.domain

import android.net.Uri
import com.ewingelen.chatter.call.domain.UserPresenceRepository
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.domain.model.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ChatInteractor {

    suspend fun chat(id: String): Flow<Chat>

    suspend fun sendMessage(chatId: String, message: Message)

    suspend fun sendFiles(chatId: String, files: List<Uri>)

    fun checkUserPresenceInCall(): Flow<Boolean>

    fun deleteMessage(chatId: String, message: Message)

    suspend fun editMessage(chatId: String, newText: String, position: Int)

    class Base @Inject constructor(
        private val repository: ChatRepository,
        private val userPresenceRepository: UserPresenceRepository
    ) : ChatInteractor {

        override suspend fun chat(id: String) = repository.chat(id)

        override suspend fun sendMessage(chatId: String, message: Message) {
            repository.sendMessage(chatId, message)
        }

        override suspend fun sendFiles(chatId: String, files: List<Uri>) {
            repository.sendFiles(chatId, files)
        }

        override fun checkUserPresenceInCall() = userPresenceRepository.checkUserPresence()

        override fun deleteMessage(chatId: String, message: Message) {
            repository.deleteMessage(chatId, message)
        }

        override suspend fun editMessage(chatId: String, newText: String, position: Int) {
            repository.editMessage(chatId, newText, position)
        }
    }
}