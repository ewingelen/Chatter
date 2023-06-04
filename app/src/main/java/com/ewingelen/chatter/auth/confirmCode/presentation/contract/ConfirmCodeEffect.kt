package com.ewingelen.chatter.auth.confirmCode.presentation.contract

import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.core.presentation.Effect


interface ConfirmCodeEffect : Effect<HandleConfirmCodeEffect> {

    class SuccessSignUp : ConfirmCodeEffect {

        override fun handle(handle: HandleConfirmCodeEffect) {
            handle.successSignUp()
        }
    }

    class SuccessLogIn : ConfirmCodeEffect {

        override fun handle(handle: HandleConfirmCodeEffect) {
            handle.successLogIn()
        }
    }

    class CodeResent(private val verifyPhoneNumber: VerifyPhoneNumber) : ConfirmCodeEffect {

        override fun handle(handle: HandleConfirmCodeEffect) {
            handle.codeResent(verifyPhoneNumber)
        }
    }
}
