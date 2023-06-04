package com.ewingelen.chatter.core.presentation

import javax.inject.Inject


interface ChangePhoneNumber {

    fun change(currentNumber: String, newNumber: String): String

    class Base @Inject constructor() : ChangePhoneNumber {

        override fun change(currentNumber: String, newNumber: String) =
            if (newNumber.length < MAX_PHONE_NUMBER_LENGTH) newNumber else currentNumber

        private companion object {
            const val MAX_PHONE_NUMBER_LENGTH = 15
        }
    }
}