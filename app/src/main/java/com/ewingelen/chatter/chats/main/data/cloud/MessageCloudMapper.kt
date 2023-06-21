package com.ewingelen.chatter.chats.main.data.cloud

import com.ewingelen.chatter.core.data.cloud.model.MessageCloud
import com.ewingelen.chatter.core.data.cloud.model.ProvideUserId
import com.ewingelen.chatter.core.domain.model.AttachedFile
import com.ewingelen.chatter.core.domain.model.Message
import javax.inject.Inject

class MessageCloudMapper @Inject constructor(
    private val provideUserId: ProvideUserId
) : MessageCloud.Mapper<Message> {

    override fun map(
        id: String,
        text: String,
        senderId: String,
        file: AttachedFile?
    ): Message {
        val mine = senderId == provideUserId.provide()
        return Message(id = id, text = text, mine = mine, file = file)
    }
}