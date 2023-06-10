package com.ewingelen.chatter.auth.verifyPhone.presentation.contract

data class PhoneNumberState(
    val phoneNumber: String = "",
    val loading: Boolean = false,
    var error: String = "",
    var errorVisible: Boolean = false
)