package com.ewingelen.chatter.chats.create.domain

import com.ewingelen.chatter.core.domain.model.Chat

interface CreateChatRepository {

    suspend fun createChat(chat: Chat)
}