package com.ewingelen.chatter.auth.confirmCode.domain

interface HandleConfirmCode {

    suspend fun auth(): Auth

    fun onSuccess(newUser: Boolean)

    fun onFail(error: String)
}