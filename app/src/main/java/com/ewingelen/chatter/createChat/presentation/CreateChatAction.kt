package com.ewingelen.chatter.createChat.presentation

import com.ewingelen.chatter.core.presentation.Action

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
interface CreateChatAction : Action<HandleCreateChatAction> {

    class ChangeName(private val newName: String) : CreateChatAction {

        override fun handle(handle: HandleCreateChatAction) {
            handle.changeName(newName)
        }
    }

    class ChangePhoneNumber(private val newNumber: String) : CreateChatAction {

        override fun handle(handle: HandleCreateChatAction) {
            handle.changePhoneNumber(newNumber)
        }
    }

    class CreateChat : CreateChatAction {

        override fun handle(handle: HandleCreateChatAction) {
            handle.createChat()
        }
    }
}