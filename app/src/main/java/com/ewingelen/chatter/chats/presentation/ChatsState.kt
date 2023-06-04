package com.ewingelen.chatter.chats.presentation

data class ChatsState(
    val chats: List<ChatUi> = emptyList(),
)