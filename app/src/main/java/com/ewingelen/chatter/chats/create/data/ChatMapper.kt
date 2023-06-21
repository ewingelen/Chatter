package com.ewingelen.chatter.chats.create.data

import com.ewingelen.chatter.core.data.ProvideUserIdByPhoneNumber
import com.ewingelen.chatter.core.data.cloud.model.ChatCloud
import com.ewingelen.chatter.core.data.cloud.model.MessageCloud
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.domain.model.Message
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class ChatMapper @Inject constructor(
    private val messagesMapper: Message.Mapper<MessageCloud>,
    private val provideUserIdByPhoneNumber: ProvideUserIdByPhoneNumber
) : Chat.Mapper<ChatCloud> {

    override suspend fun map(
        id: String,
        membersId: List<String>,
        contactName: String,
        contactSurname: String,
        contactPhoneNumber: String,
        contactPhoto: String,
        messages: List<Message>
    ): ChatCloud {
        val messagesCloud = messages.map { message ->
            message.map(messagesMapper)
        }
        val membersIdMapped = listOf(
            Firebase.auth.uid!!,
            provideUserIdByPhoneNumber.provide(contactPhoneNumber)
        )
        return ChatCloud(
            id = id,
            membersId = membersIdMapped,
            messages = messagesCloud
        )
    }
}