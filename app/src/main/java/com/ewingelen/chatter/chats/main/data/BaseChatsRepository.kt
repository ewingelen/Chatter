package com.ewingelen.chatter.chats.main.data

import com.ewingelen.chatter.chats.main.data.cloud.ChatsCloudDataSource
import com.ewingelen.chatter.chats.main.domain.ChatsRepository
import com.ewingelen.chatter.core.data.cloud.model.ChatCloud
import com.ewingelen.chatter.core.domain.model.Chat
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BaseChatsRepository @Inject constructor(
    private val cloudDataSource: ChatsCloudDataSource,
    private val chatCloudMapper: ChatCloud.Mapper<Chat>,
) : ChatsRepository {

    override fun fetchChats() = cloudDataSource.fetchChats().map {
        it.map { chatCloud ->
            chatCloud.map(chatCloudMapper)
        }
    }

    override fun deleteChat(id: String) {
        cloudDataSource.deleteChat(id)
    }
}

