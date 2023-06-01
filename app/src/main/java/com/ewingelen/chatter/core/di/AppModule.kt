package com.ewingelen.chatter.core.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.ewingelen.chatter.core.data.local.ChatterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val DATABASE_NAME = "app_database"
    private const val PREFERENCES_NAME = "app_preferences"
    private val Context.dataStore by preferencesDataStore(PREFERENCES_NAME)

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context) = context.dataStore

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ChatterDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
}