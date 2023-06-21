package com.ewingelen.chatter.auth.createProfile.data.cloud

import androidx.annotation.Keep
import com.google.firebase.firestore.PropertyName

@Keep
data class UserInitialCloud(
    @get:PropertyName("id")
    @set:PropertyName("id")
    var id: String = "",
    @get:PropertyName("phoneNumber")
    @set:PropertyName("phoneNumber")
    var phoneNumber: String = "",
    @get:PropertyName("name")
    @set:PropertyName("name")
    var name: String = "",
    @get:PropertyName("surname")
    @set:PropertyName("surname")
    var surname: String = "",
    @get:PropertyName("avatarUrl")
    @set:PropertyName("avatarUrl")
    var avatarUrl: String = ""
)