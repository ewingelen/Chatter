package com.ewingelen.chatter.createChat.data

import com.ewingelen.chatter.core.data.cloud.model.ChatCloud
import com.ewingelen.chatter.core.data.cache.model.ChatLocal
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.createChat.data.cloud.CreateChatCloudDataSource
import com.ewingelen.chatter.createChat.data.cache.CreateChatCacheDataSource
import com.ewingelen.chatter.createChat.domain.CreateChatRepository
import javax.inject.Inject

class BaseCreateChatRepository @Inject constructor(
    private val cloudDataSource: CreateChatCloudDataSource,
    private val cacheDataSource: CreateChatCacheDataSource,
    private val toLocalMapper: Chat.Mapper<ChatLocal>,
    private val toCloudMapper: Chat.Mapper<ChatCloud>
) : CreateChatRepository {

    override suspend fun createChat(chat: Chat) {
        try {
            val chatLocal = chat.map(toLocalMapper)
            val chatCloud = chat.map(toCloudMapper)
            cloudDataSource.createChat(chatCloud)
            cacheDataSource.createChat(chatLocal)
        } catch (e: Exception) {
            throw e
        }
    }
}