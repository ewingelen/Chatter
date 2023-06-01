package com.ewingelen.chatter.core.data.cloud.model

import com.google.firebase.firestore.PropertyName

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
data class MessageCloud(
    @PropertyName("text")
    val text: String,
    @PropertyName("mine")
    val mine: Boolean
)