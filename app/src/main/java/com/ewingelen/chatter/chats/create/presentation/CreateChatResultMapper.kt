package com.ewingelen.chatter.chats.create.presentation

import com.ewingelen.chatter.chats.create.domain.CreateChatResult
import javax.inject.Inject

class CreateChatResultMapper @Inject constructor(
    private val communication: CreateChatCommunication
) : CreateChatResult.Mapper {

    override fun map() {
        communication.chatCreated()
    }

    override fun map(error: String) {
        communication.showCreateChatError(error)
    }
}