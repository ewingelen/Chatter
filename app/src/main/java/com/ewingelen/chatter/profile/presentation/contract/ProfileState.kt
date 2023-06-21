package com.ewingelen.chatter.profile.presentation.contract

data class ProfileState(
    val name: String = "",
    val surname: String = "",
    val phoneNumber: String = "",
    val photo: String = ""
)
