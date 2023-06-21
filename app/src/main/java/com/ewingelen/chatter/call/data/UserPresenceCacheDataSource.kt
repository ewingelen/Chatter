package com.ewingelen.chatter.call.data

import com.ewingelen.chatter.core.data.cache.AppPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserPresenceCacheDataSource {

    suspend fun changeUserPresence(presence: Boolean)

    fun checkUserPresence(): Flow<Boolean>

    class Base @Inject constructor(
        private val preferences: AppPreferences
    ) : UserPresenceCacheDataSource {

        override suspend fun changeUserPresence(presence: Boolean) {
            preferences.save(PRESENCE_KEY, presence)
        }

        override fun checkUserPresence() = preferences.read(PRESENCE_KEY)

        private companion object {
            const val PRESENCE_KEY = "user_presence"
        }
    }
}