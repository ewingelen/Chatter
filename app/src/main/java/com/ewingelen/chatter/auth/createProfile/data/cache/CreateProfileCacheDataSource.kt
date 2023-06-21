package com.ewingelen.chatter.auth.createProfile.data.cache

import com.ewingelen.chatter.auth.createProfile.data.UserInitialData
import com.ewingelen.chatter.core.data.cache.model.UserLocal
import timber.log.Timber
import javax.inject.Inject

interface CreateProfileCacheDataSource {

    suspend fun saveUser(userInitial: UserInitialData)

    class Base @Inject constructor(
        private val dao: CreateProfileDao,
        private val userInitialDataMapper: UserInitialData.Mapper<UserLocal>,
    ) : CreateProfileCacheDataSource {

        override suspend fun saveUser(userInitial: UserInitialData) {
            val userLocal = userInitial.map(userInitialDataMapper)
            dao.saveUser(userLocal)
        }
    }
}