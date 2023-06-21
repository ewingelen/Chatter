package com.ewingelen.chatter.chats.create.data

import androidx.annotation.Keep
import com.google.firebase.firestore.PropertyName

@Keep
data class SavedContactCloud(
    @get:PropertyName("contactId")
    @set:PropertyName("contactId")
    var contactId: String = "",
    @get:PropertyName("contactName")
    @set:PropertyName("contactName")
    var contactName: String = "",
    @get:PropertyName("contactSurname")
    @set:PropertyName("contactSurname")
    var contactSurname: String = "",
    @get:PropertyName("contactPhoneNumber")
    @set:PropertyName("contactPhoneNumber")
    var contactPhoneNumber: String = ""
)