package com.ewingelen.chatter.chat.presentation.model

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
data class ChatInfoUi(
    val contactName: String = "",
    val messages: List<MessageUi> = emptyList(),
)