package com.ewingelen.chatter.auth.confirmCode.data.cache

import com.ewingelen.chatter.core.data.local.AppPreferences
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 07.05.2023.
 */
interface ConfirmCodeCacheDataSource {

    suspend fun saveUser(id: String)

    suspend fun checkUserAuthorized(): Boolean

    class Base @Inject constructor(
        private val preferences: AppPreferences
    ) : ConfirmCodeCacheDataSource {

        override suspend fun saveUser(id: String) {
            preferences.save(USER_ID_KEY, id)
        }

        override suspend fun checkUserAuthorized() =
            preferences.read(USER_ID_KEY).first().isNotEmpty()

        private companion object {
            const val USER_ID_KEY = "user_id"
        }
    }
}