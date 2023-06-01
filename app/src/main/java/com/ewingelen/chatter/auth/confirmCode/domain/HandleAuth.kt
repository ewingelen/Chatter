package com.ewingelen.chatter.auth.confirmCode.domain

interface HandleAuth {

    suspend fun auth(): Auth

    fun success(userExists: Boolean)

    fun failure(error: String)
}