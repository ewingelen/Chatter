package com.ewingelen.chatter.chat.presentation.contract

import com.ewingelen.chatter.chat.presentation.model.ChatInfoUi


data class ChatState(
    val chatInfo: ChatInfoUi = ChatInfoUi(),
    val enteredMessage: String = ""
)