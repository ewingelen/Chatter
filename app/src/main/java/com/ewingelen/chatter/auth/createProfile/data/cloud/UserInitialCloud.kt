package com.ewingelen.chatter.auth.createProfile.data.cloud

import com.google.firebase.firestore.PropertyName

data class UserInitialCloud(
    @PropertyName("id")
    val id: String = "",
    @PropertyName("phone_number")
    val phoneNumber: String = "",
    @PropertyName("name")
    val name: String = "",
    @PropertyName("surname")
    val surname: String = "",
    @PropertyName("avatar_url")
    val avatarUrl: String = ""
)