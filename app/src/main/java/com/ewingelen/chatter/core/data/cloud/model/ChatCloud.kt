package com.ewingelen.chatter.core.data.cloud.model

import com.google.firebase.firestore.PropertyName

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
data class ChatCloud(
    @PropertyName("contact_id")
    val contactId: String,
    @PropertyName("contact_name")
    val contactName: String,
    @PropertyName("contact_phone_number")
    val contactPhoneNumber: String,
    @PropertyName("messages")
    val messages: List<MessageCloud>
) {
    fun samePhoneNumber(phoneNumber: String) = phoneNumber == contactPhoneNumber
}