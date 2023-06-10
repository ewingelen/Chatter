package com.ewingelen.chatter.createChat.data.cache

import com.ewingelen.chatter.core.data.cache.model.ChatLocal
import javax.inject.Inject


interface CreateChatCacheDataSource {

    suspend fun createChat(chat: ChatLocal)

    class Base @Inject constructor(private val dao: CreateChatDao) : CreateChatCacheDataSource {

        override suspend fun createChat(chat: ChatLocal) {
            dao.addChat(chat)
        }
    }
}