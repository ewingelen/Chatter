package com.ewingelen.chatter.chats.di

import com.ewingelen.chatter.chats.domain.ChatsInteractor
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
    fun bindChatsInteractor(interactor: ChatsInteractor.Base): ChatsInteractor
}