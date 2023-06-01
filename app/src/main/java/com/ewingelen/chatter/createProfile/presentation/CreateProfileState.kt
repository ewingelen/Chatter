package com.ewingelen.chatter.createProfile.presentation

import android.net.Uri

data class CreateProfileState(
    val name: String = "",
    val photo: Uri = Uri.EMPTY,
    val errorVisible: Boolean = false
)