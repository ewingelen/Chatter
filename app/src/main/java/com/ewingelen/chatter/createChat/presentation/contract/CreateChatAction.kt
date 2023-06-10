package com.ewingelen.chatter.createChat.presentation.contract

import com.ewingelen.chatter.core.presentation.Action


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