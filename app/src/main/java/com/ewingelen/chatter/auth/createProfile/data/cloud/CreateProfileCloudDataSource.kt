package com.ewingelen.chatter.auth.createProfile.data.cloud

import com.ewingelen.chatter.auth.createProfile.data.UserInitialData
import com.ewingelen.chatter.core.data.cloud.ProvideUserReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

//TODO: Unite methods, think about auth algorithm, don't allow auth in exception other then internet
interface CreateProfileCloudDataSource {

    suspend fun createProfile(userInitial: UserInitialData)

    class Base @Inject constructor(
        private val userInitialDataMapper: UserInitialData.Mapper<UserInitialCloud>,
        private val provideUserReference: ProvideUserReference.Document
    ) : CreateProfileCloudDataSource {

        override suspend fun createProfile(userInitial: UserInitialData) {
            val userInitialCloud = userInitial.map(userInitialDataMapper)
            provideUserReference.document().set(userInitialCloud).await()
        }
    }
}