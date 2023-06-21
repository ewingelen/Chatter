package com.ewingelen.chatter.chats.main.presentation.contract

import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.presentation.Action

interface ChatsAction : Action<HandleChatsAction> {

    class DeleteChat(private val chat: Chat) : ChatsAction {

        override fun handle(handle: HandleChatsAction) {
            handle.deleteChat(chat)
        }
    }
}