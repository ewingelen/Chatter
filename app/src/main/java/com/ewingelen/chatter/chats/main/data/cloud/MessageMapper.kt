package com.ewingelen.chatter.chats.main.data.cloud

import com.ewingelen.chatter.core.data.cloud.model.MessageCloud
import com.ewingelen.chatter.core.data.cloud.model.ProvideUserId
import com.ewingelen.chatter.core.domain.model.AttachedFile
import com.ewingelen.chatter.core.domain.model.Message
import javax.inject.Inject

class MessageMapper @Inject constructor(
    private val provideUserId: ProvideUserId
) : Message.Mapper<MessageCloud> {

    override fun map(id: String, text: String, mine: Boolean, file: AttachedFile?) = MessageCloud(
        id = id,
        text = text,
        senderId = provideUserId.provide(),
        file = file
    )
}