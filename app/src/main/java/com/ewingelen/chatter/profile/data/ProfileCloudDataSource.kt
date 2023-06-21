package com.ewingelen.chatter.profile.data

import com.ewingelen.chatter.core.data.cloud.ProvideUserReference
import com.ewingelen.chatter.core.data.cloud.model.UserCloud
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface ProfileCloudDataSource {

    suspend fun fetchInfo(): UserCloud

    class Base @Inject constructor(
        private val provideUserReference: ProvideUserReference.Document
    ) : ProfileCloudDataSource {

        override suspend fun fetchInfo(): UserCloud {
            val user = provideUserReference.document().get().await()
            return user.toObject(UserCloud::class.java) ?: UserCloud()
        }
    }
}