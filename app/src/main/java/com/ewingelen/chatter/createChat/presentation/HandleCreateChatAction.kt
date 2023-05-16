package com.ewingelen.chatter.createChat.presentation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
interface HandleCreateChatAction {

    fun changeName(newName: String)

    fun changePhoneNumber(newNumber: String)

    fun createChat()
}