package com.ewingelen.chatter.createChat.data

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
interface CreateChatCloudDataSource {

    fun createChat()

    class Base: CreateChatCloudDataSource {

        override fun createChat() {
        }
    }
}