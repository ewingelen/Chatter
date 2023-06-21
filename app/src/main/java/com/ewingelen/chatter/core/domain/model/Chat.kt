package com.ewingelen.chatter.core.domain.model

data class Chat(
    val id: String = "",
    val membersId: List<String> = emptyList(),
    val contactName: String = "",
    val contactSurname: String = "",
    val contactPhoneNumber: String = "",
    val contactPhoto: String = "",
    val messages: List<Message> = emptyList(),
    //TODO
//    val time: String,
//    val unreadMessagesCount: Int,
) {
    interface Mapper<T> {

        suspend fun map(
            id: String,
            membersId: List<String>,
            contactName: String,
            contactSurname: String,
            contactPhoneNumber: String,
            contactPhoto: String,
            messages: List<Message>
        ): T
    }

    suspend fun <T> map(mapper: Mapper<T>) = mapper.map(
        id = id,
        membersId = membersId,
        contactName = contactName,
        contactSurname = contactSurname,
        contactPhoneNumber = contactPhoneNumber,
        contactPhoto = contactPhoto,
        messages = messages
    )
}