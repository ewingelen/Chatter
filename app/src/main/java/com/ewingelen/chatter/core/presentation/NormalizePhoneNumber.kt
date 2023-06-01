package com.ewingelen.chatter.core.presentation

import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 17.05.2023.
 */
interface NormalizePhoneNumber {

    fun normalize(phoneNumber: String): String

    class Base @Inject constructor() : NormalizePhoneNumber {

        //TODO: check
        override fun normalize(phoneNumber: String) = "+$phoneNumber".trim()
    }
}