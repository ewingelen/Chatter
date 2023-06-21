package com.ewingelen.chatter.call.di

import com.ewingelen.chatter.call.data.BaseUserPresenceRepository
import com.ewingelen.chatter.call.data.UserPresenceCacheDataSource
import com.ewingelen.chatter.call.domain.UserPresenceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface CallModule {

    @Binds
    @ViewModelScoped
    fun bindUserPresenceCacheDataSource(
        cacheDataSource: UserPresenceCacheDataSource.Base
    ): UserPresenceCacheDataSource

    @Binds
    @ViewModelScoped
    fun bindUserPresenceRepository(repository: BaseUserPresenceRepository): UserPresenceRepository
}