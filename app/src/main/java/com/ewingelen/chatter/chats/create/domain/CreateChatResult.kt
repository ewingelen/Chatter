package com.ewingelen.chatter.chats.create.domain

interface CreateChatResult {

    interface Mapper {

        fun map()

        fun map(error: String)
    }

    fun map(mapper: Mapper)

    class Success : CreateChatResult {

        override fun map(mapper: Mapper) = mapper.map()
    }

    class Fail(private val error: String) : CreateChatResult {

        override fun map(mapper: Mapper) = mapper.map(error)
    }
}