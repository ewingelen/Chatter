package com.ewingelen.chatter.chats.create.presentation.contract

interface HandleCreateChatAction {

    fun changeName(newName: String)

    fun changeSurname(newSurname: String)

    fun changePhoneNumber(newNumber: String)

    fun createChat()
}