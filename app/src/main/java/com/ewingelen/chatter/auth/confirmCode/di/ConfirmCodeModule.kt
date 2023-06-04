package com.ewingelen.chatter.auth.confirmCode.di

import android.os.CountDownTimer
import com.ewingelen.chatter.auth.confirmCode.data.BaseConfirmCodeRepository
import com.ewingelen.chatter.auth.confirmCode.data.ConfirmCodeCloudDataSource
import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCode
import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeErrorMapper
import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeInteractor
import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeRepository
import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeResult
import com.ewingelen.chatter.auth.confirmCode.domain.ResentCodeTimer
import com.ewingelen.chatter.auth.confirmCode.presentation.mapper.BaseConfirmCodeErrorMapper
import com.ewingelen.chatter.auth.confirmCode.presentation.communication.ConfirmCodeCommunication
import com.ewingelen.chatter.auth.confirmCode.presentation.communication.ConfirmCodeResultCommunication
import com.ewingelen.chatter.auth.confirmCode.presentation.mapper.ConfirmCodeResultMapper
import com.ewingelen.chatter.auth.confirmCode.presentation.communication.ResentCodeTimerCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface ConfirmCodeModule {

    @Binds
    @ViewModelScoped
    fun bindCloudDataSource(cloudDataSource: ConfirmCodeCloudDataSource.Base):
            ConfirmCodeCloudDataSource

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
    fun bindResentCodeTimer(resentCodeTimer: ResentCodeTimer): CountDownTimer

    @Binds
    @ViewModelScoped
    fun bindConfirmCode(confirmCode: ConfirmCode.Base): ConfirmCode

    @Binds
    @ViewModelScoped
    fun bindCommunication(communication: ConfirmCodeCommunication.Base): ConfirmCodeCommunication

    @Binds
    @ViewModelScoped
    fun bindConfirmCodeResultCommunication(communication: ConfirmCodeCommunication.Base):
            ConfirmCodeResultCommunication

    @Binds
    @ViewModelScoped
    fun bindResentCodeTimerCommunication(ommunication: ConfirmCodeCommunication.Base):
            ResentCodeTimerCommunication

    @Binds
    @ViewModelScoped
    fun bindConfirmCodeResultMapper(mapper: ConfirmCodeResultMapper): ConfirmCodeResult.Mapper<Unit>
}