package com.ewingelen.chatter.chat.presentation.contract


interface HandleChatAction {

    fun changeMessage(newMessage: String)

    fun sendMessage()
}