package com.ewingelen.chatter.chat.domain

import android.net.Uri
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun chat(id: String): Flow<Chat>

    suspend fun sendMessage(chatId: String, message: Message)

    suspend fun sendFiles(chatId: String, files: List<Uri>)

    fun deleteMessage(chatId: String, message: Message)

    suspend fun editMessage(chatId: String, newText: String, position: Int)
}