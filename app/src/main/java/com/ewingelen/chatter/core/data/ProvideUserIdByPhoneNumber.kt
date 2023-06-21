package com.ewingelen.chatter.core.data

import com.ewingelen.chatter.core.data.cloud.ProvideUserReference
import com.ewingelen.chatter.core.data.cloud.model.UserCloud
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface ProvideUserIdByPhoneNumber {

    suspend fun provide(phoneNumber: String): String

    class Base @Inject constructor(
        private val provideUserReference: ProvideUserReference.Collection
    ) : ProvideUserIdByPhoneNumber {

        override suspend fun provide(phoneNumber: String): String {
            val users = provideUserReference.collection()
                .whereEqualTo("phoneNumber", phoneNumber)
                .get()
                .await()
            return if (users.documents.isNotEmpty()) {
                users.documents.first().toObject(UserCloud::class.java)?.id ?: ""
            } else {
                ""
            }
        }
    }
}