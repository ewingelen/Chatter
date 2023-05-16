package com.ewingelen.chatter.auth.confirmCode.presentation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 30.04.2023.
 */
data class ConfirmCodeState(
    val code: String = "",
    val verificationId: String = "",
    val phoneNumber: String = "",
    val timeToResentLabel: String = "",
    val loading: Boolean = false,
    val resentCodeEnabled: Boolean = false,
    val error: String = ""
)
