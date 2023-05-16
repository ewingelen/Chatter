package com.ewingelen.chatter.chat.di

import com.ewingelen.chatter.chat.domain.ChatInteractor
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
interface ChatModule {

    @Binds
    @ViewModelScoped
    fun bindInteractor(interactor: ChatInteractor.Base): ChatInteractor
}