package com.ewingelen.chatter.auth.core.di

import com.ewingelen.chatter.auth.verifyPhone.presentation.VerificationErrorMapper
import com.ewingelen.chatter.core.presentation.ChangePhoneNumber
import com.ewingelen.chatter.core.presentation.NormalizePhoneNumber
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface AuthModule {

    @Binds
    @ViewModelScoped
    fun bindVerificationErrorMapper(verificationErrorMapper: VerificationErrorMapper.Base):
            VerificationErrorMapper

    @Binds
    @ViewModelScoped
    fun bindChangePhoneNumber(changePhoneNumber: ChangePhoneNumber.Base): ChangePhoneNumber

    @Binds
    @ViewModelScoped
    fun bindNormalizePhoneNumber(changePhoneNumber: NormalizePhoneNumber.Base): NormalizePhoneNumber
}