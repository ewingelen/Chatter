package com.ewingelen.chatter.chat.presentation

import com.ewingelen.chatter.core.presentation.Action


interface ChatAction : Action<HandleChatAction> {

    class ChangeMessage(private val newMessage: String) : ChatAction {

        override fun handle(handle: HandleChatAction) {
            handle.changeMessage(newMessage)
        }
    }

    class SendMessage: ChatAction {

        override fun handle(handle: HandleChatAction) {
            handle.sendMessage()
        }
    }
}