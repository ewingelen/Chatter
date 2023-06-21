package com.ewingelen.chatter.chats.create.presentation.contract

import com.ewingelen.chatter.core.presentation.Effect

interface CreateChatEffect : Effect<HandleCreateChatEffect> {

    class ChatCreated : CreateChatEffect {

        override fun handle(handle: HandleCreateChatEffect) {
            handle.chatCreated()
        }
    }
}