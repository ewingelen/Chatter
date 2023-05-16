package com.ewingelen.chatter.createChat.presentation

import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
interface CreateChatInteractor {

    fun createChat()

    class Base @Inject constructor(): CreateChatInteractor {

        override fun createChat() {
        }
    }
}