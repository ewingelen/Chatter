package com.ewingelen.chatter.chat.presentation

import com.ewingelen.chatter.chat.presentation.model.ChatInfoUi

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
data class ChatState(
    val chatInfo: ChatInfoUi = ChatInfoUi(),
    val enteredMessage: String = ""
)