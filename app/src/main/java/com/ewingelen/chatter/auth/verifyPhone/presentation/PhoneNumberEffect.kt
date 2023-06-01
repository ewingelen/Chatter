package com.ewingelen.chatter.auth.verifyPhone.presentation

import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.core.presentation.Effect

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 30.04.2023.
 */
interface PhoneNumberEffect : Effect<HandlePhoneNumberEffect> {

    class VerificationStarted(
        private val verifyPhoneNumber: VerifyPhoneNumber
    ) : PhoneNumberEffect {

        override fun handle(handle: HandlePhoneNumberEffect) {
            handle.startVerification(verifyPhoneNumber)
        }
    }

    class VerificationCompleted : PhoneNumberEffect {

        override fun handle(handle: HandlePhoneNumberEffect) {
            handle.completeVerification()
        }
    }

    class CodeSent(
        private val verificationId: String,
        private val phoneNumber: String
    ) : PhoneNumberEffect {

        override fun handle(handle: HandlePhoneNumberEffect) {
            handle.continueVerification(verificationId, phoneNumber)
        }
    }
}