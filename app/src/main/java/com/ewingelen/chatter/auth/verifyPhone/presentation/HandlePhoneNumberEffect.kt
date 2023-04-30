package com.ewingelen.chatter.auth.verifyPhone.presentation

import com.ewingelen.chatter.core.presentation.HandleEffect

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 30.04.2023.
 */
interface HandlePhoneNumberEffect : HandleEffect {

    fun startVerification(verifyPhoneNumber: VerifyPhoneNumber)

    fun completeVerification()

    fun navigateToTheCodeScreen(verificationId: String)
}