package com.ewingelen.chatter.core.domain.model

data class Message(
    val id: String = "",
    val mine: Boolean,
    val text: String,
    val file: AttachedFile? = null
) {
    interface Mapper<T> {

        fun map(id: String, text: String, mine: Boolean, file: AttachedFile?): T
    }

    fun <T> map(mapper: Mapper<T>) = mapper.map(id = id, text = text, mine = mine, file = file)
}