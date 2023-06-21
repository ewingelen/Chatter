package com.ewingelen.chatter.chats.main.di

import com.ewingelen.chatter.chats.main.data.BaseChatsRepository
import com.ewingelen.chatter.chats.main.data.cloud.ChatCloudMapper
import com.ewingelen.chatter.chats.main.data.cloud.ChatsCloudDataSource
import com.ewingelen.chatter.chats.main.data.cloud.MessageCloudMapper
import com.ewingelen.chatter.chats.main.domain.ChatsInteractor
import com.ewingelen.chatter.chats.main.domain.ChatsRepository
import com.ewingelen.chatter.chats.main.presentation.ChatsCommunication
import com.ewingelen.chatter.core.data.cloud.model.ChatCloud
import com.ewingelen.chatter.core.data.cloud.model.MessageCloud
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.domain.model.Message
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface ChatsModule {

    @Binds
    @ViewModelScoped
    fun bindCloudDataSource(cloudDataSource: ChatsCloudDataSource.Base): ChatsCloudDataSource

    @Binds
    @ViewModelScoped
    fun bindChatCloudMapper(mapper: ChatCloudMapper): ChatCloud.Mapper<Chat>

    @Binds
    @ViewModelScoped
    fun bindMessageCloudMapper(mapper: MessageCloudMapper): MessageCloud.Mapper<Message>

    @Binds
    @ViewModelScoped
    fun bindRepository(repository: BaseChatsRepository): ChatsRepository

    @Binds
    @ViewModelScoped
    fun bindChatsInteractor(interactor: ChatsInteractor.Base): ChatsInteractor

    @Binds
    @ViewModelScoped
    fun bindsChatsCommunication(communication: ChatsCommunication.Base): ChatsCommunication
}