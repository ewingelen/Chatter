package com.ewingelen.chatter.auth.createProfile.domain

interface CreateProfileResult {

    interface Mapper {

        fun map()

        fun map(error: String)
    }

    fun map(mapper: Mapper)

    class Success : CreateProfileResult {

        override fun map(mapper: Mapper) = mapper.map()
    }

    class Fail(private val error: String) : CreateProfileResult {

        override fun map(mapper: Mapper) = mapper.map(error)
    }
}