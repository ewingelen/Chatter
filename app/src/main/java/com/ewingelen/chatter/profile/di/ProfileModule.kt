package com.ewingelen.chatter.profile.di

import com.ewingelen.chatter.profile.data.BaseProfileRepository
import com.ewingelen.chatter.profile.data.ProfileCloudDataSource
import com.ewingelen.chatter.profile.domain.ProfileRepository
import com.ewingelen.chatter.profile.presentation.contract.ProfileCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface ProfileModule {

    @Binds
    @ViewModelScoped
    fun bindCloudDataSource(cloudDataSource: ProfileCloudDataSource.Base): ProfileCloudDataSource

    @Binds
    @ViewModelScoped
    fun bindRepository(repository: BaseProfileRepository): ProfileRepository

    @Binds
    @ViewModelScoped
    fun bindCommunication(communication: ProfileCommunication.Base): ProfileCommunication
}