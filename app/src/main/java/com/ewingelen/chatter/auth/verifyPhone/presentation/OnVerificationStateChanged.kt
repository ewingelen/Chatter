package com.ewingelen.chatter.auth.verifyPhone.presentation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
interface OnVerificationStateChanged {

    fun onVerificationCompleted()

    fun onVerificationFailed(e: Exception)

    fun onCodeSent(verificationId: String)
}