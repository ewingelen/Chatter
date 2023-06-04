package com.ewingelen.chatter.chats.presentation

data class ChatUi(
    val userId: String,
    val userAvatar: Int,
    val userName: String,
    val lastMessage: String,
    val time: String,
    val unreadMessagesCount: Int
)