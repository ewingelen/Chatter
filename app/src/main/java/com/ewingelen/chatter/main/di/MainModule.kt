package com.ewingelen.chatter.main.di

import com.ewingelen.chatter.main.domain.MainInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 13.05.2023.
 */
@Module
@InstallIn(ViewModelComponent::class)
interface MainModule {

    @Binds
    @ViewModelScoped
    fun bindInteractor(interactor: MainInteractor.Base): MainInteractor
}