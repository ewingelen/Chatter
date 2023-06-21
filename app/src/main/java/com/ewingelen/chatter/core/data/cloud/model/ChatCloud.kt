package com.ewingelen.chatter.core.data.cloud.model

import androidx.annotation.Keep
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.PropertyName
import kotlinx.coroutines.tasks.await

@Keep
data class ChatCloud(
    @get:PropertyName("id")
    @set:PropertyName("id")
    var id: String = "",
    @get:PropertyName("membersId")
    @set:PropertyName("membersId")
    var membersId: List<String> = emptyList(),
    @get:PropertyName("messages")
    @set:PropertyName("messages")
    var messages: List<MessageCloud> = emptyList()
) {

    interface Mapper<T> {

        suspend fun map(
            id: String,
            membersId: List<String>,
            messages: List<MessageCloud>
        ): T
    }

    suspend fun <T> map(mapper: Mapper<T>) = mapper.map(
        id = id,
        membersId = membersId,
        messages = messages
    )

    suspend fun userExists(
        usersCollection: CollectionReference,
        phoneNumber: String
    ): Boolean {
        val users = usersCollection.whereEqualTo(PHONE_NUMBER_FIELD, phoneNumber).get()
            .await()
        return users.documents.size > 0
    }

    private companion object {
        const val PHONE_NUMBER_FIELD = "phoneNumber"
    }
}