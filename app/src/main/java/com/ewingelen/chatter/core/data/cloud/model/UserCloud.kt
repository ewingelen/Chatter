package com.ewingelen.chatter.core.data.cloud.model

import com.google.firebase.firestore.PropertyName

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
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