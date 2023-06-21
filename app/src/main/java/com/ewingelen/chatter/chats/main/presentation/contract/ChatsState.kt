package com.ewingelen.chatter.chats.main.presentation.contract

import com.ewingelen.chatter.core.domain.model.Chat

data class ChatsState(
    val chats: List<Chat> = emptyList(),
    val topBarTitle: String = "",
    val loading: Boolean = true
)