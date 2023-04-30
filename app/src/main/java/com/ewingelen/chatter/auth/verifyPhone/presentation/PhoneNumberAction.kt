package com.ewingelen.chatter.auth.verifyPhone.presentation

import com.ewingelen.chatter.core.presentation.Action

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 29.04.2023.
 */
interface PhoneNumberAction : Action<HandlePhoneNumberAction> {

    class ChangePhoneNumber(private val newNumber: String) : PhoneNumberAction {

        override fun handle(handle: HandlePhoneNumberAction) {
            handle.changePhoneNumber(newNumber)
        }
    }

    class SignUp : PhoneNumberAction {

        override fun handle(handle: HandlePhoneNumberAction) {
            handle.verifyPhoneNumber()
        }
    }
}