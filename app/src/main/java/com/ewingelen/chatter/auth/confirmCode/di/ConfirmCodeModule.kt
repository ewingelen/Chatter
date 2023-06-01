package com.ewingelen.chatter.auth.confirmCode.di

import com.ewingelen.chatter.auth.confirmCode.data.BaseConfirmCodeRepository
import com.ewingelen.chatter.auth.confirmCode.data.cache.ConfirmCodeCacheDataSource
import com.ewingelen.chatter.auth.confirmCode.data.cloud.ConfirmCodeCloudDataSource
import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeErrorMapper
import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeInteractor
import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeRepository
import com.ewingelen.chatter.auth.confirmCode.presentation.BaseConfirmCodeErrorMapper
import com.ewingelen.chatter.auth.confirmCode.presentation.ConfirmCode
import com.ewingelen.chatter.auth.confirmCode.presentation.ResentCodeTimer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 01.05.2023.
 */
@Module
@InstallIn(ViewModelComponent::class)
interface ConfirmCodeModule {

    @Binds
    @ViewModelScoped
    fun bindCloudDataSource(cloudDataSource: ConfirmCodeCloudDataSource.Base):
            ConfirmCodeCloudDataSource

    @Binds
    @ViewModelScoped
    fun bindCacheDataSource(cacheDataSource: ConfirmCodeCacheDataSource.Base):
            ConfirmCodeCacheDataSource

    @Binds
    @ViewModelScoped
    fun bindRepository(repository: BaseConfirmCodeRepository): ConfirmCodeRepository

    @Binds
    @ViewModelScoped
    fun bindInteractor(interactor: ConfirmCodeInteractor.Base): ConfirmCodeInteractor

    @Binds
    @ViewModelScoped
    fun bindErrorMapper(mapper: BaseConfirmCodeErrorMapper): ConfirmCodeErrorMapper

    @Binds
    @ViewModelScoped
    fun bindResentCodeTimer(resentCodeTimer: ResentCodeTimer.Base):
            ResentCodeTimer

    @Binds
    @ViewModelScoped
    fun bindConfirmCode(confirmCode: ConfirmCode.Base): ConfirmCode
}