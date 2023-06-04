package com.ewingelen.chatter.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers


@Module
@InstallIn(ViewModelComponent::class)
class DispatchersModule {

    @Provides
    @ViewModelScoped
    fun bindIODispatcher() = Dispatchers.IO
}