package com.ewingelen.chatter.chats.presentation.contract

import com.ewingelen.chatter.chats.presentation.ChatUi

data class ChatsState(
    val chats: List<ChatUi> = emptyList(),
    val topBarTitle: String = ""
)