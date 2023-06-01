package com.ewingelen.chatter.createChat.presentation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
data class CreateChatState(
    val phoneNumber: String = "",
    val errorEmptyPhoneNumberShowing: Boolean = false,
    val errorEmptyPhoneNumber: String = "",
    val name: String = "",
    val errorEmptyNameShowing: Boolean = false,
    val errorEmptyName: String = "",
    val error: String = ""
)