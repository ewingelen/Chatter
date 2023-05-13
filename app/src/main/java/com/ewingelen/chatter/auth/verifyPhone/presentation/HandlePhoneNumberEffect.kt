package com.ewingelen.chatter.auth.verifyPhone.presentation

import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.core.presentation.HandleEffect

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 30.04.2023.
 */
interface HandlePhoneNumberEffect : HandleEffect {

    fun startVerification(verify: VerifyPhoneNumber)

    fun completeVerification()

    fun navigateToTheCodeScreen(verificationId: String, phoneNumber: String)
}