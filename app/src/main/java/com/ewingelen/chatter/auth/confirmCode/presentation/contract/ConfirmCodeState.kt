package com.ewingelen.chatter.auth.confirmCode.presentation.contract

data class ConfirmCodeState(
    val code: String = "",
    val phoneNumber: String = "",
    val timeToResent: String = "",
    val loading: Boolean = false,
    val resentCodeEnabled: Boolean = false,
    val error: String = ""
)
