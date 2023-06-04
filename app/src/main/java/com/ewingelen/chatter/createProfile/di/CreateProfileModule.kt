package com.ewingelen.chatter.createProfile.di

import com.ewingelen.chatter.createProfile.data.BaseCreateProfileRepository
import com.ewingelen.chatter.createProfile.data.CreateProfileCloudDataSource
import com.ewingelen.chatter.createProfile.domain.CreateProfileInteractor
import com.ewingelen.chatter.createProfile.domain.CreateProfileRepository
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
    fun bindRepository(repository: BaseCreateProfileRepository): CreateProfileRepository

    @Binds
    @ViewModelScoped
    fun bindInteractor(interactor: CreateProfileInteractor.Base): CreateProfileInteractor

//    @Binds
//    @ViewModelScoped
//    fun bindHandleCreateProfileError(handleError: HandleCreateProfileResult):
//            HandleError<CreateProfileResult>
}