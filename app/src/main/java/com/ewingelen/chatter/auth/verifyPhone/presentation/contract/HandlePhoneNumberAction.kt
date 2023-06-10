package com.ewingelen.chatter.auth.verifyPhone.presentation.contract

interface HandlePhoneNumberAction {

    fun changePhoneNumber(newNumber: String)

    fun verifyPhoneNumber()
}