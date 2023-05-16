package com.ewingelen.chatter.auth.verifyPhone.presentation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
interface HandlePhoneNumberAction {

    fun changePhoneNumber(newNumber: String)

    fun verifyPhoneNumber()
}