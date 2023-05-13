package com.ewingelen.chatter.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 07.05.2023.
 */
interface AppPreferences {

    suspend fun save(key: String, value: String)

    fun read(key: String): Flow<String>

    class Base @Inject constructor(private val dataStore: DataStore<Preferences>) : AppPreferences {

        override suspend fun save(key: String, value: String) {
            dataStore.edit { preferences ->
                val preferencesKey = stringPreferencesKey(key)
                preferences[preferencesKey] = value
            }
        }

        override fun read(key: String) = dataStore.data
            .catch { e ->
                if (e is IOException) {
                    emit(emptyPreferences())
                }
            }
            .map { preferences ->
                val preferencesKey = stringPreferencesKey(key)
                preferences[preferencesKey] ?: ""
            }
    }
}