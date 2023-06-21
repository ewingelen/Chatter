package com.ewingelen.chatter.core.data.cloud.model

import androidx.annotation.Keep
import com.ewingelen.chatter.chats.create.data.SavedContactCloud
import com.google.firebase.firestore.PropertyName

@Keep
data class UserCloud(
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
    @get:PropertyName("chats")
    @set:PropertyName("chats")
    var chats: List<ChatCloud> = emptyList(),
    @get:PropertyName("avatarUrl")
    @set:PropertyName("avatarUrl")
    var avatarUrl: String = "",
    @get:PropertyName("savedContacts")
    @set:PropertyName("savedContacts")
    var savedContacts: List<SavedContactCloud> = emptyList()
)