package com.ewingelen.chatter.chat.presentation.contract

import com.ewingelen.chatter.core.domain.model.Chat

data class ChatState(
    val chat: Chat = Chat(),
    val enteredMessage: String = "",
    val loading: Boolean = true,
    val userPresenceInCall: Boolean = false
)