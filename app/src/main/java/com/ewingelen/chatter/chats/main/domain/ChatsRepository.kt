package com.ewingelen.chatter.chats.main.domain

import com.ewingelen.chatter.core.domain.model.Chat
import kotlinx.coroutines.flow.Flow

interface ChatsRepository {

    fun fetchChats(): Flow<List<Chat>>

    fun deleteChat(id: String)
}