package com.ewingelen.chatter.auth.createProfile.presentation

import android.net.Uri
import com.ewingelen.chatter.auth.createProfile.presentation.contract.CreateProfileEffect
import com.ewingelen.chatter.auth.createProfile.presentation.contract.CreateProfileState
import com.ewingelen.chatter.core.presentation.BaseEffectCommunication
import com.ewingelen.chatter.core.presentation.ObserveUi
import javax.inject.Inject

interface CreateProfileCommunication : ObserveUi<CreateProfileState.Base, CreateProfileEffect> {

    fun changeName(newName: String)

    fun changeSurname(newSurname: String)

    fun addPhoto(photo: Uri)

    fun launchEmptyNameWarning()

    fun stopEmptyNameWarning()

    fun showCreateProfileError(error: String)

    fun showNoInternetConnectionWarning(warning: String)

    fun showLoading()

    fun profileCreated()

    class Base @Inject constructor() :
        BaseEffectCommunication<CreateProfileState.Base, CreateProfileEffect>
            (defaultState = CreateProfileState.Base()),
        CreateProfileCommunication {

        override fun changeName(newName: String) {
            updateState(state.value.copy(name = newName))
        }

        override fun changeSurname(newSurname: String) {
            updateState(state.value.copy(surname = newSurname))
        }

        override fun addPhoto(photo: Uri) {
            updateState(state.value.copy(photo = photo))
        }

        override fun launchEmptyNameWarning() {
            updateState(state.value.copy(emptyNameErrorShowing = true))
        }

        override fun stopEmptyNameWarning() {
            updateState(state.value.copy(emptyNameErrorShowing = false))
        }

        override fun showCreateProfileError(error: String) {
            updateState(
                state.value.copy(
                    createProfileError = error,
                    createProfileErrorVisible = true,
                    noInternetWarningVisible = false,
                    loading = false
                )
            )
        }

        override fun showNoInternetConnectionWarning(warning: String) {
            updateState(state.value.copy(noInternetWarningVisible = true))
        }

        override fun showLoading() {
            updateState(state.value.copy(loading = true, noInternetWarningVisible = false))
        }

        override fun profileCreated() {
            sendEffect(CreateProfileEffect.ProfileCreated())
        }
    }
}