package com.ewingelen.chatter.main.di

import com.ewingelen.chatter.main.presentation.CheckUserAuthorized
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface MainModule {

    @Binds
    @ViewModelScoped
    fun bindCheckUserAuthorized(checkUserAuthorized: CheckUserAuthorized.Base): CheckUserAuthorized
}