package com.ewingelen.chatter.createChat.presentation


interface HandleCreateChatAction {

    fun changeName(newName: String)

    fun changePhoneNumber(newNumber: String)

    fun createChat()
}