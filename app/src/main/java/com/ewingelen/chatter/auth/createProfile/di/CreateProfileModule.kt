package com.ewingelen.chatter.auth.createProfile.di

import com.ewingelen.chatter.auth.createProfile.data.BaseCreateProfileRepository
import com.ewingelen.chatter.auth.createProfile.data.ProvidePhotoPath
import com.ewingelen.chatter.auth.createProfile.data.UserInitialData
import com.ewingelen.chatter.auth.createProfile.data.UserInitialMapper
import com.ewingelen.chatter.auth.createProfile.data.cache.CreateProfileCacheDataSource
import com.ewingelen.chatter.auth.createProfile.data.cache.UserInitialDataToUserLocalMapper
import com.ewingelen.chatter.auth.createProfile.data.cloud.CreateProfileCloudDataSource
import com.ewingelen.chatter.auth.createProfile.data.cloud.UserInitialCloud
import com.ewingelen.chatter.auth.createProfile.data.cloud.UserInitialDataToUserInitialCloudMapper
import com.ewingelen.chatter.auth.createProfile.domain.CreateProfileInteractor
import com.ewingelen.chatter.auth.createProfile.domain.CreateProfileRepository
import com.ewingelen.chatter.auth.createProfile.domain.CreateProfileResult
import com.ewingelen.chatter.auth.createProfile.domain.HandleCreateProfileError
import com.ewingelen.chatter.auth.createProfile.domain.UserInitial
import com.ewingelen.chatter.auth.createProfile.presentation.CreateProfileCommunication
import com.ewingelen.chatter.auth.createProfile.presentation.CreateProfileResultMapper
import com.ewingelen.chatter.core.data.cache.model.UserLocal
import com.ewingelen.chatter.core.domain.HandleDomainError

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface CreateProfileModule {

    @Binds
    @ViewModelScoped
    fun bindCloudDataSource(cloudDataSource: CreateProfileCloudDataSource.Base):
            CreateProfileCloudDataSource

    @Binds
    @ViewModelScoped
    fun bindUserInitialDataToUserInitialCloudMapper(
        mapper: UserInitialDataToUserInitialCloudMapper
    ): UserInitialData.Mapper<UserInitialCloud>

    @Binds
    @ViewModelScoped
    fun bindProvidePhotoPath(providePhotoPath: ProvidePhotoPath.Base): ProvidePhotoPath

    @Binds
    @ViewModelScoped
    fun bindCacheDataSource(cacheDataSource: CreateProfileCacheDataSource.Base):
            CreateProfileCacheDataSource

    @Binds
    @ViewModelScoped
    fun bindUserInitialDataToUserLocalMapper(
        mapper: UserInitialDataToUserLocalMapper
    ): UserInitialData.Mapper<UserLocal>

    @Binds
    @ViewModelScoped
    fun bindRepository(repository: BaseCreateProfileRepository): CreateProfileRepository

    @Binds
    @ViewModelScoped
    fun bindUserInitialMapper(mapper: UserInitialMapper): UserInitial.Mapper<UserInitialData>

    @Binds
    @ViewModelScoped
    fun bindInteractor(interactor: CreateProfileInteractor.Base): CreateProfileInteractor

    @Binds
    @ViewModelScoped
    fun bindCommunication(communication: CreateProfileCommunication.Base):
            CreateProfileCommunication

    @Binds
    @ViewModelScoped
    fun bindCreateProfileResultMapper(mapper: CreateProfileResultMapper): CreateProfileResult.Mapper

    @Binds
    @ViewModelScoped
    fun bindHandleCreateProfileError(handleError: HandleCreateProfileError):
            HandleDomainError<String>
}