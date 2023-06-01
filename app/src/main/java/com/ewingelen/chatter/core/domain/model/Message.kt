package com.ewingelen.chatter.core.domain.model

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 17.05.2023.
 */
data class Message(
    private val text: String,
    private val mine: Boolean,
) {
    interface Mapper<T> {

        fun map(text: String, mine: Boolean): T
    }

    fun <T> map(mapper: Mapper<T>) = mapper.map(text, mine)
}