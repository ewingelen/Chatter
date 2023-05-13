package com.ewingelen.chatter.core.di

import com.ewingelen.chatter.core.data.AppPreferences
import com.ewingelen.chatter.core.data.BaseProvideResources
import com.ewingelen.chatter.core.domain.ProvideResources
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 06.05.2023.
 */
@Module
@InstallIn(ViewModelComponent::class)
interface CoreModule {

    @Binds
    @ViewModelScoped
    fun bindProvideResources(provideResources: BaseProvideResources): ProvideResources

    @Binds
    @ViewModelScoped
    fun bindsAppPreferences(preferences: AppPreferences.Base): AppPreferences
}