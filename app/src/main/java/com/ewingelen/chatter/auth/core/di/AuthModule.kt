package com.ewingelen.chatter.auth.core.di

import com.ewingelen.chatter.auth.verifyPhone.presentation.VerificationErrorMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 07.05.2023.
 */
@Module
@InstallIn(ViewModelComponent::class)
interface AuthModule {

    @Binds
    @ViewModelScoped
    fun bindVerificationErrorMapper(verificationErrorMapper: VerificationErrorMapper.Base):
            VerificationErrorMapper
}