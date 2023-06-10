package com.ewingelen.chatter.auth.confirmCode.presentation.communication

interface ConfirmCodeResultCommunication {

    fun successAuth(isNewUser: Boolean)

    fun showError(error: String)
}