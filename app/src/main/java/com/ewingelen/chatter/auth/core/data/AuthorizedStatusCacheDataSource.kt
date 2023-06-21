package com.ewingelen.chatter.auth.core.data

import com.ewingelen.chatter.core.data.cache.AppPreferences
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface AuthorizedStatusCacheDataSource {

    suspend fun authorize()

    suspend fun checkUserAuthorized(): Boolean

    suspend fun signOut()

    class Base @Inject constructor(private val preferences: AppPreferences) :
        AuthorizedStatusCacheDataSource {

        override suspend fun authorize() {
            preferences.save(USER_AUTHORIZED, true)
        }

        override suspend fun checkUserAuthorized() = preferences.read(USER_AUTHORIZED).first()

        override suspend fun signOut() {
            preferences.save(USER_AUTHORIZED, false)
        }

        private companion object {
            const val USER_AUTHORIZED = "user_authorized"
        }
    }
}