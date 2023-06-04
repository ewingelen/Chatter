package com.ewingelen.chatter.chat.presentation


interface HandleChatAction {

    fun changeMessage(newMessage: String)

    fun sendMessage()
}