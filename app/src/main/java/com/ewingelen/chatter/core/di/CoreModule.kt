package com.ewingelen.chatter.core.di

import com.ewingelen.chatter.core.ObserveInternetConnection
import com.ewingelen.chatter.core.data.HandleDataError
import com.ewingelen.chatter.core.data.cache.AppPreferences
import com.ewingelen.chatter.core.data.cache.SavePhoto
import com.ewingelen.chatter.core.data.cloud.HandleFirebaseRequestError
import com.ewingelen.chatter.core.data.cloud.ProvideUserReference
import com.ewingelen.chatter.core.data.cloud.UploadPhoto
import com.ewingelen.chatter.core.data.cloud.model.ProvideUserId
import com.ewingelen.chatter.core.domain.ProvideResources
import com.ewingelen.chatter.core.presentation.BaseProvideResources
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface CoreModule {

    @Binds
    @ViewModelScoped
    fun bindProvideResources(provideResources: BaseProvideResources): ProvideResources

    @Binds
    @ViewModelScoped
    fun bindsAppPreferences(preferences: AppPreferences.Base): AppPreferences

    @Binds
    @ViewModelScoped
    fun bindProvideUserCollection(provideUserReference: ProvideUserReference.Base):
            ProvideUserReference.Collection

    @Binds
    @ViewModelScoped
    fun bindProvideUserDocument(provideUserReference: ProvideUserReference.Base):
            ProvideUserReference.Document

    @Binds
    @ViewModelScoped
    fun bindProvideUserId(provideUserId: ProvideUserId.Base): ProvideUserId

    @Binds
    @ViewModelScoped
    fun bindSavePhoto(savePhoto: SavePhoto.Base): SavePhoto

    @Binds
    @ViewModelScoped
    fun bindUploadPhoto(savePhoto: UploadPhoto.Base): UploadPhoto

    @Binds
    @ViewModelScoped
    fun bindHandleFirebaseRequestError(handleError: HandleFirebaseRequestError):
            HandleDataError

    @Binds
    @ViewModelScoped
    fun bindObserveInternetConnection(observeInternetConnection: ObserveInternetConnection.Base):
            ObserveInternetConnection
}