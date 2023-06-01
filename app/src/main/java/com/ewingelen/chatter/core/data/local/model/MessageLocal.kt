package com.ewingelen.chatter.core.data.local.model

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 17.05.2023.
 */
data class MessageLocal(
    private val text: String,
    private val mine: Boolean
)