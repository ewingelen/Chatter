package com.ewingelen.chatter.auth.verifyPhone.presentation.contract

import com.ewingelen.chatter.core.presentation.Action

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