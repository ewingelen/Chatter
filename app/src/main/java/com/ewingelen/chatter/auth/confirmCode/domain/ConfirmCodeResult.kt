package com.ewingelen.chatter.auth.confirmCode.domain

interface ConfirmCodeResult {

    interface Mapper<T> {

        fun map(newUser: Boolean): T

        fun map(error: String): T
    }

    fun <T> map(mapper: Mapper<T>): T

    class Success(private val newUser: Boolean) : ConfirmCodeResult {

        override fun <T> map(mapper: Mapper<T>) = mapper.map(newUser)
    }

    class Fail(private val error: String) : ConfirmCodeResult {

        override fun <T> map(mapper: Mapper<T>) = mapper.map(error)
    }
}