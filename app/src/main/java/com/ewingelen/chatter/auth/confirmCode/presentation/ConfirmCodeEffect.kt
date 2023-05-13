package com.ewingelen.chatter.auth.confirmCode.presentation

import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.core.presentation.Effect

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 06.05.2023.
 */
interface ConfirmCodeEffect : Effect<HandleConfirmCodeEffect> {

    class AuthSuccess : ConfirmCodeEffect {

        override fun handle(handleEffect: HandleConfirmCodeEffect) {
            handleEffect.authSuccess()
        }
    }

    class ResentCode(private val verifyPhoneNumber: VerifyPhoneNumber) : ConfirmCodeEffect {

        override fun handle(handleEffect: HandleConfirmCodeEffect) {
            handleEffect.resentCode(verifyPhoneNumber)
        }
    }
}
