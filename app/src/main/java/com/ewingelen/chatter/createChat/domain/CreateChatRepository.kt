package com.ewingelen.chatter.createChat.domain

import com.ewingelen.chatter.core.domain.model.Chat

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 17.05.2023.
 */
interface CreateChatRepository {

    suspend fun createChat(chat: Chat)
}