package com.ewingelen.chatter.chats.domain

import com.ewingelen.chatter.chats.presentation.ChatUi
import javax.inject.Inject

interface ChatsInteractor {

    fun fetchChats(): List<ChatUi>

    class Base @Inject constructor(): ChatsInteractor {

        override fun fetchChats(): List<ChatUi> {
            return listOf(
                ChatUi(
                    userId = "r134314fqrqe",
                    userAvatar = 0,
                    userName = "Rebeca Donelli",
                    lastMessage = "Pls take a look at the image I sent",
                    time = "16:04",
                    unreadMessagesCount = 2
                ),
            )
        }
    }
}