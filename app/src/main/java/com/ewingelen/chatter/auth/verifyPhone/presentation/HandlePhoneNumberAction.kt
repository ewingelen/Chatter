package com.ewingelen.chatter.auth.verifyPhone.presentation


interface HandlePhoneNumberAction {

    fun changePhoneNumber(newNumber: String)

    fun verifyPhoneNumber()
}