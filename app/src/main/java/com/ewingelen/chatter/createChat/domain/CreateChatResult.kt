package com.ewingelen.chatter.createChat.domain


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