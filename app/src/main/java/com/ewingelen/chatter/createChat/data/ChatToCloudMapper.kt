package com.ewingelen.chatter.createChat.data

import com.ewingelen.chatter.core.data.cloud.model.ChatCloud
import com.ewingelen.chatter.core.data.cloud.model.MessageCloud
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.domain.model.Message
import javax.inject.Inject


class ChatToCloudMapper @Inject constructor(
    private val messagesMapper: Message.Mapper<MessageCloud>
) : Chat.Mapper<ChatCloud> {

    override fun map(
        contactId: String,
        contactName: String,
        contactPhoneNumber: String,
        messages: List<Message>
    ): ChatCloud {
        val messagesCloud = messages.map { message ->
            message.map(messagesMapper)
        }
        return ChatCloud(contactId, contactName, contactPhoneNumber, messagesCloud)
    }
}