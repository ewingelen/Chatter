package com.ewingelen.chatter.chat.domain

import com.ewingelen.chatter.chat.presentation.model.ChatInfoUi
import com.ewingelen.chatter.chat.presentation.model.MessageUi
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
interface ChatInteractor {

    fun chatInfo(contactId: String): ChatInfoUi

    fun sendMessage()

    class Base @Inject constructor() : ChatInteractor {

        override fun chatInfo(contactId: String): ChatInfoUi {
            return ChatInfoUi(
                contactName = "Rebeca Donelli",
                messages = listOf(
                    MessageUi("Hi", false),
                    MessageUi("Hello", true),
                    MessageUi("How are you?", false),
                    MessageUi("I am good. How are you?", true),
                )
            )
        }

        override fun sendMessage() {
        }
    }
}