package com.ewingelen.chatter.chat.presentation

import com.ewingelen.chatter.chat.presentation.model.ChatInfoUi


data class ChatState(
    val chatInfo: ChatInfoUi = ChatInfoUi(),
    val enteredMessage: String = ""
)