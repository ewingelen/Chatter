package com.ewingelen.chatter.createChat.di

import com.ewingelen.chatter.core.data.local.ChatterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 17.05.2023.
 */
@Module
@InstallIn(ViewModelComponent::class)
class CreateChatDaoModule {

    @Provides
    @ViewModelScoped
    fun provideDao(database: ChatterDatabase) = database.createChatDao()

}