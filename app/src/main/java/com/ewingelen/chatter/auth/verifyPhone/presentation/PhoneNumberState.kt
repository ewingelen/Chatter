package com.ewingelen.chatter.auth.verifyPhone.presentation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
data class PhoneNumberState(
    val phoneNumber: String = "",
    val loading: Boolean = false,
    var error: String = "",
)