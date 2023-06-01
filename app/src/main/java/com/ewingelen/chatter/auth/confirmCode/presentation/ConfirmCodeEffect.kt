package com.ewingelen.chatter.auth.confirmCode.presentation

import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.core.presentation.Effect

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 06.05.2023.
 */
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
