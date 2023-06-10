package com.ewingelen.chatter.auth.verifyPhone.presentation.contract

import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.core.presentation.Effect

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