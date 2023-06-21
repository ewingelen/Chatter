package com.ewingelen.chatter.chats.create.di

import com.ewingelen.chatter.chats.create.data.BaseCreateChatRepository
import com.ewingelen.chatter.chats.create.data.ChatMapper
import com.ewingelen.chatter.chats.create.data.CreateChatCloudDataSource
import com.ewingelen.chatter.chats.create.data.HandleCreateChatDataError
import com.ewingelen.chatter.chats.create.domain.CreateChatInteractor
import com.ewingelen.chatter.chats.create.domain.CreateChatRepository
import com.ewingelen.chatter.chats.create.domain.CreateChatResult
import com.ewingelen.chatter.chats.create.domain.HandleCreateChatDomainError
import com.ewingelen.chatter.chats.create.presentation.CreateChatCommunication
import com.ewingelen.chatter.chats.create.presentation.CreateChatResultMapper
import com.ewingelen.chatter.chats.main.data.cloud.MessageMapper
import com.ewingelen.chatter.core.data.HandleDataError
import com.ewingelen.chatter.core.data.cloud.model.ChatCloud
import com.ewingelen.chatter.core.data.cloud.model.MessageCloud
import com.ewingelen.chatter.core.domain.HandleDomainError
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.domain.model.Message
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
interface CreateChatModule {

    @Binds
    @ViewModelScoped
    fun bindCloudDataSource(cloudDataSource: CreateChatCloudDataSource.Base):
            CreateChatCloudDataSource

    @Binds
    @ViewModelScoped
    fun bindChatMapper(mapper: ChatMapper): Chat.Mapper<ChatCloud>

    @Binds
    @ViewModelScoped
    fun bindMessageMapper(mapper: MessageMapper): Message.Mapper<MessageCloud>

    @Binds
    @ViewModelScoped
    fun bindRepository(repository: BaseCreateChatRepository): CreateChatRepository

    @Binds
    @ViewModelScoped
    fun bindInteractor(interactor: CreateChatInteractor.Base): CreateChatInteractor

    @Binds
    @ViewModelScoped
    @Named("CreateChat")
    fun bindHandleCreateChatError(handleError: HandleCreateChatDataError): HandleDataError

    @Binds
    @ViewModelScoped
    @Named("CreateChat")
    fun bindHandleCreateChatDomainError(handleError: HandleCreateChatDomainError):
            HandleDomainError<String>

    @Binds
    @ViewModelScoped
    fun bindCreateChatResultMapper(mapper: CreateChatResultMapper): CreateChatResult.Mapper

    @Binds
    @ViewModelScoped
    fun bindCreateChatCommunication(communication: CreateChatCommunication.Base):
            CreateChatCommunication
}