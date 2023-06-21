package com.ewingelen.chatter.chats.create.data

import com.ewingelen.chatter.chats.create.domain.CreateChatRepository
import com.ewingelen.chatter.core.data.HandleDataError
import com.ewingelen.chatter.core.data.ProvideUserIdByPhoneNumber
import com.ewingelen.chatter.core.data.cloud.model.ChatCloud
import com.ewingelen.chatter.core.domain.model.Chat
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class BaseCreateChatRepository @Inject constructor(
    private val cloudDataSource: CreateChatCloudDataSource,
    private val chatToCloudMapper: Chat.Mapper<ChatCloud>,
    @Named("CreateChat")
    private val handleError: HandleDataError,
    private val provideUserIdByPhoneNumber: ProvideUserIdByPhoneNumber
) : CreateChatRepository {

    override suspend fun createChat(chat: Chat) = handleError.handle {
        Timber.d(chat.id.toString() + " CHAT ID 1")
        val chatCloud = chat.map(chatToCloudMapper)
        Timber.d(chatCloud.id.toString() + " CHAT ID 2")
        cloudDataSource.createChat(
            chatCloud,
            SavedContactCloud(
                contactId = provideUserIdByPhoneNumber.provide(chat.contactPhoneNumber),
                contactName = chat.contactName,
                contactSurname = chat.contactSurname,
                contactPhoneNumber = chat.contactPhoneNumber
            )
        )
    }
}