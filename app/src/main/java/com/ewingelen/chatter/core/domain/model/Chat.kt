package com.ewingelen.chatter.core.domain.model


data class Chat(
    private val contactId: String = "",
    private val contactName: String,
    private val contactPhoneNumber: String,
    private val messages: List<Message> = emptyList()
) {
    interface Mapper<T> {

        fun map(
            contactId: String,
            contactName: String,
            contactPhoneNumber: String,
            messages: List<Message>
        ): T
    }

    fun <T> map(mapper: Mapper<T>) =
        mapper.map(contactId, contactName, contactPhoneNumber, messages)
}