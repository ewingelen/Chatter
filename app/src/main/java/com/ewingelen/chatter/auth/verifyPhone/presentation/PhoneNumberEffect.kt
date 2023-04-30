package com.ewingelen.chatter.auth.verifyPhone.presentation

import com.ewingelen.chatter.core.presentation.Effect

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 30.04.2023.
 */
interface PhoneNumberEffect : Effect<HandlePhoneNumberEffect> {

    class VerificationStarted(
        private val verifyPhoneNumber: VerifyPhoneNumber
    ) : PhoneNumberEffect {

        override fun handle(handleEffect: HandlePhoneNumberEffect) {
            handleEffect.startVerification(verifyPhoneNumber)
        }
    }

    class VerificationCompleted : PhoneNumberEffect {

        override fun handle(handleEffect: HandlePhoneNumberEffect) {
            handleEffect.completeVerification()
        }
    }

    class CodeSent(private val verificationId: String) : PhoneNumberEffect {

        override fun handle(handleEffect: HandlePhoneNumberEffect) {
            handleEffect.navigateToTheCodeScreen(verificationId)
        }
    }
}