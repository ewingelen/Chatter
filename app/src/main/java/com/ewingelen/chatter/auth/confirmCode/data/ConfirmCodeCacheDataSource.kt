package com.ewingelen.chatter.auth.confirmCode.data

import com.ewingelen.chatter.core.data.AppPreferences
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 07.05.2023.
 */
interface ConfirmCodeCacheDataSource {

    suspend fun saveUser(id: String)

    suspend fun fetchUserId(): String

    class Base @Inject constructor(
        private val preferences: AppPreferences
    ) : ConfirmCodeCacheDataSource {

        override suspend fun saveUser(id: String) {
            preferences.save(PREFERENCE_KEY, id)
        }

        override suspend fun fetchUserId() = preferences.read(PREFERENCE_KEY).first()

        private companion object {
            const val PREFERENCE_KEY = "user_id"
        }
    }
}