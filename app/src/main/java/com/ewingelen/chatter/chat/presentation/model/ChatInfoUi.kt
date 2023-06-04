package com.ewingelen.chatter.chat.presentation.model


data class ChatInfoUi(
    val contactName: String = "",
    val messages: List<MessageUi> = emptyList(),
)