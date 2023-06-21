package com.ewingelen.chatter.chats.main.data.cloud

import com.ewingelen.chatter.core.data.cloud.ProvideUserReference
import com.ewingelen.chatter.core.data.cloud.model.ChatCloud
import com.ewingelen.chatter.core.data.cloud.model.MessageCloud
import com.ewingelen.chatter.core.data.cloud.model.ProvideUserId
import com.ewingelen.chatter.core.data.cloud.model.UserCloud
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.domain.model.Message
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChatCloudMapper @Inject constructor(
    private val messageCloudMapper: MessageCloud.Mapper<Message>,
    private val provideUserReference: ProvideUserReference.All,
    private val provideUserId: ProvideUserId
) : ChatCloud.Mapper<Chat> {

    override suspend fun map(
        id: String,
        membersId: List<String>,
        messages: List<MessageCloud>
    ): Chat {
        val messagesMapped = messages.map { message ->
            message.map(messageCloudMapper)
        }
        val contactId = membersId.first { memberId ->
            memberId != provideUserId.provide()
        }
        val contactSnapshot = provideUserReference.collection().document(contactId).get().await()
        val contact = contactSnapshot.toObject(UserCloud::class.java)!!

        val user = provideUserReference.document().get().await().toObject(UserCloud::class.java)
        val savedContact = user?.savedContacts?.firstOrNull() {
            it.contactId == contactId
        }
        return Chat(
            id = id,
            membersId = membersId,
            messages = messagesMapped,
            contactName = savedContact?.contactName ?: contact.name,
            contactSurname = savedContact?.contactSurname ?: contact.surname,
            contactPhoneNumber = savedContact?.contactPhoneNumber ?: contact.phoneNumber,
            contactPhoto = contact.avatarUrl
        )
    }
}