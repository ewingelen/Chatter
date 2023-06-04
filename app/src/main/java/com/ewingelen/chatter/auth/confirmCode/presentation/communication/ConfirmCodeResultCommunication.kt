package com.ewingelen.chatter.auth.confirmCode.presentation.communication

interface ConfirmCodeResultCommunication {

    fun successSignUp()

    fun successLogIn()

    fun showError(error: String)
}