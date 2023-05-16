package com.ewingelen.chatter.chat.presentation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
interface HandleChatAction {

    fun changeMessage(newMessage: String)

    fun sendMessage()
}