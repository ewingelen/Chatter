package com.ewingelen.chatter.chats.presentation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 14.05.2023.
 */
data class ChatUi(
    val userId: String,
    val userAvatar: Int,
    val userName: String,
    val lastMessage: String,
    val time: String,
    val unreadMessagesCount: Int
)