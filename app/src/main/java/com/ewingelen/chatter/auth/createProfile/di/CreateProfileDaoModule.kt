package com.ewingelen.chatter.auth.createProfile.di

import com.ewingelen.chatter.core.data.cache.ChatterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class CreateProfileDaoModule {

    @Provides
    @ViewModelScoped
    fun provideCacheDataSource(database: ChatterDatabase) = database.createProfileDao()
}