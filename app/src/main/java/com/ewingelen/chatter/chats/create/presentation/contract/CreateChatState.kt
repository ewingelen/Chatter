package com.ewingelen.chatter.chats.create.presentation.contract

import com.ewingelen.chatter.core.domain.model.Chat

interface CreateChatState {

    fun chat(): Chat

    data class Base(
        val name: String = "",
        val surname: String = "",
        val phoneNumber: String = "",
        val createChatError: String = "",
        val createChatErrorVisible: Boolean = false,
        val phoneNumberError: String = "",
        val phoneNumberErrorVisible: Boolean = false,
        val emptyNameErrorVisible: Boolean = false,
    ) : CreateChatState {

        override fun chat() = Chat(
            contactName = name,
            contactSurname = surname,
            contactPhoneNumber = phoneNumber
        )
    }
}