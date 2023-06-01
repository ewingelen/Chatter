package com.ewingelen.chatter.createChat.data.local

import com.ewingelen.chatter.core.data.local.model.ChatLocal
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 17.05.2023.
 */
interface CreateChatCacheDataSource {

    suspend fun createChat(chat: ChatLocal)

    class Base @Inject constructor(private val dao: CreateChatDao) : CreateChatCacheDataSource {

        override suspend fun createChat(chat: ChatLocal) {
            dao.addChat(chat)
        }
    }
}