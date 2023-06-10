package com.ewingelen.chatter.auth.verifyPhone.presentation.contract

import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber

interface HandlePhoneNumberEffect {

    fun startVerification(verify: VerifyPhoneNumber)

    fun continueVerification(verificationId: String, phoneNumber: String)

    fun completeVerification()
}