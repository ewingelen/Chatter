package com.ewingelen.chatter.core.data.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

interface AppPreferences {

    suspend fun save(key: String, value: Boolean)

    fun read(key: String): Flow<Boolean>

    class Base @Inject constructor(private val dataStore: DataStore<Preferences>) : AppPreferences {

        override suspend fun save(key: String, value: Boolean) {
            dataStore.edit { preferences ->
                Timber.d(value.toString())
                val preferencesKey = booleanPreferencesKey(key)
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
                val preferencesKey = booleanPreferencesKey(key)
                preferences[preferencesKey] ?: false
            }
    }
}