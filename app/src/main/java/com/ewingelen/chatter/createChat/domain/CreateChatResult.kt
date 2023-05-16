package com.ewingelen.chatter.createChat.domain

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
interface CreateChatResult {

    fun map()

    class Success(private val userId: String) : CreateChatResult {

        override fun map() {
        }
    }

    class Fail(private val error: String) : CreateChatResult {

        override fun map() {
        }
    }
}