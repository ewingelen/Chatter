package com.ewingelen.chatter.core.presentation

import javax.inject.Inject

interface NormalizePhoneNumber {

    fun normalize(phoneNumber: String): String

    class Base @Inject constructor() : NormalizePhoneNumber {

        //TODO: check
        override fun normalize(phoneNumber: String) = "+$phoneNumber".trim()
    }
}