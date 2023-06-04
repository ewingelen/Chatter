package com.ewingelen.chatter.core.data.cloud.model

import com.google.firebase.firestore.PropertyName


data class UserCloud(
    @PropertyName("id")
    val id: String = "",
    @PropertyName("phone_number")
    val phoneNumber: String = "",
    @PropertyName("name")
    val name: String = "",
    @PropertyName("chats")
    val chats: List<ChatCloud> = emptyList()
)