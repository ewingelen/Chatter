package com.ewingelen.chatter.chat.di

import com.ewingelen.chatter.chat.data.BaseChatRepository
import com.ewingelen.chatter.chat.data.ChatCloudDataSource
import com.ewingelen.chatter.chat.data.ProvideFileNameByUri
import com.ewingelen.chatter.chat.domain.ChatInteractor
import com.ewingelen.chatter.chat.domain.ChatRepository
import com.ewingelen.chatter.chat.presentation.ChatCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface ChatModule {

    @Binds
    @ViewModelScoped
    fun bindCloudDataSource(cloudDataSource: ChatCloudDataSource.Base): ChatCloudDataSource

    @Binds
    @ViewModelScoped
    fun bindRepository(repository: BaseChatRepository): ChatRepository

    @Binds
    @ViewModelScoped
    fun bindInteractor(interactor: ChatInteractor.Base): ChatInteractor

    @Binds
    @ViewModelScoped
    fun bindCommunication(communication: ChatCommunication.Base): ChatCommunication

    @Binds
    @ViewModelScoped
    fun bindProvideFileNameByUri(provideFileNameByUri: ProvideFileNameByUri.Base):
            ProvideFileNameByUri
}