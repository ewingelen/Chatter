package com.ewingelen.chatter.chat.data

import android.net.Uri
import com.ewingelen.chatter.chat.domain.ChatRepository
import com.ewingelen.chatter.core.data.cloud.model.ChatCloud
import com.ewingelen.chatter.core.data.cloud.model.MessageCloud
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.domain.model.Message
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BaseChatRepository @Inject constructor(
    private val cloudDataSource: ChatCloudDataSource,
    private val chatCloudMapper: ChatCloud.Mapper<Chat>,
    private val messageMapper: Message.Mapper<MessageCloud>
) : ChatRepository {

    override suspend fun chat(id: String) = cloudDataSource.chat(id).map {
        it.map(chatCloudMapper)
    }

    override suspend fun sendMessage(chatId: String, message: Message) {
        val messageCloud = message.map(messageMapper)
        cloudDataSource.sendMessage(chatId, messageCloud)
    }

    override suspend fun sendFiles(chatId: String, files: List<Uri>) {
        cloudDataSource.sendFiles(chatId, files)
    }

    override fun deleteMessage(chatId: String, message: Message) {
        val messageCloud = message.map(messageMapper)
        cloudDataSource.deleteMessage(chatId, messageCloud)
    }

    override suspend fun editMessage(chatId: String, newText: String, position: Int) {
        cloudDataSource.editMessage(chatId, newText, position)
    }
}