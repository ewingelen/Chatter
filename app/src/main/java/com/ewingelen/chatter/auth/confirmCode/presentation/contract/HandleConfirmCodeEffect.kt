package com.ewingelen.chatter.auth.confirmCode.presentation.contract

import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber


interface HandleConfirmCodeEffect {

    fun successSignUp()

    fun successLogIn()

    fun codeResent(verify: VerifyPhoneNumber)
}