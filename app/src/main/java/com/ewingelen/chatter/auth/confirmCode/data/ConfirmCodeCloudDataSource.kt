package com.ewingelen.chatter.auth.confirmCode.data

import com.ewingelen.chatter.core.data.cloud.ProvideUserReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface ConfirmCodeCloudDataSource {

    suspend fun checkUserExists(): Boolean

    class Base @Inject constructor(
        private val provideUserReference: ProvideUserReference.Document
    ) : ConfirmCodeCloudDataSource {

        override suspend fun checkUserExists(): Boolean {
            val user = provideUserReference.document().get().await()
            return user.exists()
        }
    }
}