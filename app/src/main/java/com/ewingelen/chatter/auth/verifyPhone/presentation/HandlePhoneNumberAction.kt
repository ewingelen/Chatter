package com.ewingelen.chatter.auth.verifyPhone.presentation

import com.ewingelen.chatter.core.presentation.HandleAction

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
interface HandlePhoneNumberAction: HandleAction {

    fun changePhoneNumber(newNumber: String)

    fun verifyPhoneNumber()
}