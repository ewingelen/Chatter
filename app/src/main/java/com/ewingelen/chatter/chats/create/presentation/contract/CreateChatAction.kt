package com.ewingelen.chatter.chats.create.presentation.contract

import com.ewingelen.chatter.core.presentation.Action

interface CreateChatAction : Action<HandleCreateChatAction> {

    class ChangePhoneNumber(private val newNumber: String) : CreateChatAction {

        override fun handle(handle: HandleCreateChatAction) {
            handle.changePhoneNumber(newNumber)
        }
    }

    class ChangeName(private val newName: String) : CreateChatAction {

        override fun handle(handle: HandleCreateChatAction) {
            handle.changeName(newName)
        }
    }

    class ChangeSurname(private val newSurname: String) : CreateChatAction {

        override fun handle(handle: HandleCreateChatAction) {
            handle.changeSurname(newSurname)
        }
    }

    class CreateChat : CreateChatAction {

        override fun handle(handle: HandleCreateChatAction) {
            handle.createChat()
        }
    }
}