package com.ewingelen.chatter.createChat.data

import com.ewingelen.chatter.core.data.cache.model.ChatLocal
import com.ewingelen.chatter.core.data.cache.model.MessageLocal
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.domain.model.Message
import javax.inject.Inject


class ChatToLocalMapper @Inject constructor(
    private val messagesMapper: Message.Mapper<MessageLocal>
): Chat.Mapper<ChatLocal> {

    override fun map(
        contactId: String,
        contactName: String,
        contactPhoneNumber: String,
        messages: List<Message>
    ): ChatLocal {
        val messagesLocal = messages.map { message ->
            message.map(messagesMapper)
        }
        return ChatLocal(contactId, contactName, contactPhoneNumber)
    }
}