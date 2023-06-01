package com.ewingelen.chatter.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 17.05.2023.
 */
@Module
@InstallIn(ViewModelComponent::class)
class DispatchersModule {

    @Provides
    @ViewModelScoped
    fun bindIODispatcher() = Dispatchers.IO
}