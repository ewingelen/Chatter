package com.ewingelen.chatter.core.data.cloud.model

import com.google.firebase.firestore.PropertyName


data class MessageCloud(
    @PropertyName("text")
    val text: String,
    @PropertyName("mine")
    val mine: Boolean
)