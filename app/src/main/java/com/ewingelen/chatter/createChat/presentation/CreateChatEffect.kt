package com.ewingelen.chatter.createChat.presentation

import com.ewingelen.chatter.core.presentation.Effect

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 17.05.2023.
 */
interface CreateChatEffect : Effect<HandleCreateChatEffect> {

    class ChatCreated : CreateChatEffect {

        override fun handle(handle: HandleCreateChatEffect) {
            handle.chatCreated()
        }
    }
}