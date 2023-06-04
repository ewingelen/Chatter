package com.ewingelen.chatter.auth.verifyPhone.presentation


data class PhoneNumberState(
    val phoneNumber: String = "",
    val loading: Boolean = false,
    var error: String = "",
)