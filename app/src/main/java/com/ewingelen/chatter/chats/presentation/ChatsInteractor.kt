package com.ewingelen.chatter.chats.presentation

import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 14.05.2023.
 */
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
                ChatUi(
                    userId = "r134314fqrqe",
                    userAvatar = 0,
                    userName = "Rebeca Donelli",
                    lastMessage = "Pls take a look at the image I sent",
                    time = "16:04",
                    unreadMessagesCount = 2
                ),
                ChatUi(
                    userId = "r134314fqrqe",
                    userAvatar = 0,
                    userName = "Rebeca Donelli",
                    lastMessage = "Pls take a look at the image I sent",
                    time = "16:04",
                    unreadMessagesCount = 2
                )
            )
        }
    }
}