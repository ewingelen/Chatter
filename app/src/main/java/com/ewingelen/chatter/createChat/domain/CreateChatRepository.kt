package com.ewingelen.chatter.createChat.domain

import com.ewingelen.chatter.core.domain.model.Chat


interface CreateChatRepository {

    suspend fun createChat(chat: Chat)
}