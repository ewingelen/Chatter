package com.ewingelen.chatter.createChat.di

import com.ewingelen.chatter.createChat.domain.CreateChatInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
@Module
@InstallIn(ViewModelComponent::class)
interface CreateChatModule {

    @Binds
    @ViewModelScoped
    fun createChatModule(interactor: CreateChatInteractor.Base): CreateChatInteractor
}