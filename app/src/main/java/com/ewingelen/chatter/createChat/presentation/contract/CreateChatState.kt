package com.ewingelen.chatter.createChat.presentation.contract

data class CreateChatState(
    val phoneNumber: String = "",
    val errorEmptyPhoneNumberShowing: Boolean = false,
    val errorEmptyPhoneNumber: String = "",
    val name: String = "",
    val errorEmptyNameShowing: Boolean = false,
    val errorEmptyName: String = "",
    val error: String = "",
    val errorVisible: Boolean = false
)