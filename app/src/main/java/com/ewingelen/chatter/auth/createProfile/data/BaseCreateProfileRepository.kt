package com.ewingelen.chatter.auth.createProfile.data

import com.ewingelen.chatter.auth.createProfile.data.cache.CreateProfileCacheDataSource
import com.ewingelen.chatter.auth.createProfile.data.cloud.CreateProfileCloudDataSource
import com.ewingelen.chatter.auth.createProfile.domain.CreateProfileRepository
import com.ewingelen.chatter.auth.createProfile.domain.UserInitial
import com.ewingelen.chatter.core.data.HandleDataError
import javax.inject.Inject

class BaseCreateProfileRepository @Inject constructor(
    private val cloudDataSource: CreateProfileCloudDataSource,
    private val cacheDataSource: CreateProfileCacheDataSource,
    private val handleDataError: HandleDataError,
    private val userInitialMapper: UserInitial.Mapper<UserInitialData>,
) : CreateProfileRepository {

    override suspend fun createProfile(userInitial: UserInitial) = handleDataError.handle {
        val userInitialData = userInitial.map(userInitialMapper)
        cloudDataSource.createProfile(userInitialData)
        cacheDataSource.saveUser(userInitialData)
    }
}