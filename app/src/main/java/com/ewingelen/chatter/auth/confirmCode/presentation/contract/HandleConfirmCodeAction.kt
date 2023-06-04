package com.ewingelen.chatter.auth.confirmCode.presentation.contract

interface HandleConfirmCodeAction {

    fun changeCode(newCode: String)

    fun resentCode()
}