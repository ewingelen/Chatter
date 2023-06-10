package com.ewingelen.chatter.createChat.di

import com.ewingelen.chatter.createChat.data.BaseCreateChatRepository
import com.ewingelen.chatter.createChat.data.cache.CreateChatCacheDataSource
import com.ewingelen.chatter.createChat.data.cloud.CreateChatCloudDataSource
import com.ewingelen.chatter.createChat.data.cloud.CreateChatErrorMapper
import com.ewingelen.chatter.createChat.domain.CreateChatInteractor
import com.ewingelen.chatter.createChat.domain.CreateChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
interface CreateChatModule {

    @Binds
    @ViewModelScoped
    fun bindErrorMapper(mapper: CreateChatErrorMapper.Base): CreateChatErrorMapper

    @Binds
    @ViewModelScoped
    fun bindCloudDataSource(cloudDataSource: CreateChatCloudDataSource.Base):
            CreateChatCloudDataSource

    @Binds
    @ViewModelScoped
    fun bindCacheDataSource(cacheDataSource: CreateChatCacheDataSource.Base):
            CreateChatCacheDataSource

    @Binds
    @ViewModelScoped
    fun bindRepository(repository: BaseCreateChatRepository): CreateChatRepository

    @Binds
    @ViewModelScoped
    fun bindInteractor(interactor: CreateChatInteractor.Base): CreateChatInteractor

//    @Binds
//    @ViewModelScoped
//    fun bindHandleError(handleError: HandleCreateChatError): HandleDataError
}