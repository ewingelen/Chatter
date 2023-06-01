package com.ewingelen.chatter.auth.verifyPhone.presentation

import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 30.04.2023.
 */
interface HandlePhoneNumberEffect {

    fun startVerification(verify: VerifyPhoneNumber)

    fun continueVerification(verificationId: String, phoneNumber: String)

    fun completeVerification()
}