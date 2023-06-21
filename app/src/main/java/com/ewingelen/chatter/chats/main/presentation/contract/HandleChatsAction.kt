package com.ewingelen.chatter.chats.main.presentation.contract

import com.ewingelen.chatter.core.domain.model.Chat

interface HandleChatsAction {

    fun deleteChat(chat: Chat)
}