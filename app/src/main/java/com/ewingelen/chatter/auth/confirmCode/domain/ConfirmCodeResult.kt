package com.ewingelen.chatter.auth.confirmCode.domain

interface ConfirmCodeResult {

    interface Mapper {

        fun map(isNewUser: Boolean)

        fun map(error: String)
    }

    fun map(mapper: Mapper)

    class Success(private val userExists: Boolean) : ConfirmCodeResult {

        override fun map(mapper: Mapper) = mapper.map(userExists)
    }

    class Fail(private val error: String) : ConfirmCodeResult {

        override fun map(mapper: Mapper) = mapper.map(error)
    }
}