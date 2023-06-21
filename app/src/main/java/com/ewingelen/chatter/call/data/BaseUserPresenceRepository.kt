package com.ewingelen.chatter.call.data

import com.ewingelen.chatter.call.domain.UserPresenceRepository
import javax.inject.Inject

class BaseUserPresenceRepository @Inject constructor(
    private val cacheDataSource: UserPresenceCacheDataSource
): UserPresenceRepository {

    override suspend fun changeUserPresence(presence: Boolean) {
        cacheDataSource.changeUserPresence(presence)
    }

    override fun checkUserPresence() = cacheDataSource.checkUserPresence()
}