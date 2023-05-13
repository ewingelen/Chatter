package com.ewingelen.chatter.auth.confirmCode.presentation

import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.core.presentation.HandleEffect

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 06.05.2023.
 */
interface HandleConfirmCodeEffect : HandleEffect {

    fun authSuccess()

    fun resentCode(verify: VerifyPhoneNumber)
}