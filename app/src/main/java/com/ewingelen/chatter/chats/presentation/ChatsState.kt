package com.ewingelen.chatter.chats.presentation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 14.05.2023.
 */
data class ChatsState(
    val chats: List<ChatUi> = emptyList(),
)