package com.ewingelen.chatter.auth.createProfile.presentation

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.ewingelen.chatter.R
import com.ewingelen.chatter.auth.createProfile.domain.CreateProfileInteractor
import com.ewingelen.chatter.auth.createProfile.domain.CreateProfileResult
import com.ewingelen.chatter.auth.createProfile.presentation.contract.CreateProfileAction
import com.ewingelen.chatter.auth.createProfile.presentation.contract.CreateProfileEffect
import com.ewingelen.chatter.auth.createProfile.presentation.contract.CreateProfileState
import com.ewingelen.chatter.auth.createProfile.presentation.contract.HandleCreateProfileAction
import com.ewingelen.chatter.core.ObserveInternetConnection
import com.ewingelen.chatter.core.domain.ProvideResources
import com.ewingelen.chatter.core.presentation.BaseNewEffectViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProfileViewModel @Inject constructor(
    private val interactor: CreateProfileInteractor,
    private val communication: CreateProfileCommunication,
    private val createProfileResultMapper: CreateProfileResult.Mapper,
    private val observeInternetConnection: ObserveInternetConnection,
    private val provideResources: ProvideResources
) : BaseNewEffectViewModel<CreateProfileState.Base, CreateProfileAction, CreateProfileEffect>
    (communication),
    HandleCreateProfileAction {

    private val state = communication.state()

    override fun handleAction(action: CreateProfileAction) {
        action.handle(this)
    }

    override fun changeName(newName: String) {
        if (newName.length < MAX_NAME_LENGTH) {
            communication.changeName(newName)
        }
    }

    override fun changeSurname(newSurname: String) {
        if (newSurname.length < MAX_NAME_LENGTH) {
            communication.changeSurname(newSurname)
        }
    }

    override fun addPhoto(newPhoto: Uri) {
        communication.addPhoto(newPhoto)
    }

    //TODO: check
    override fun createProfile() {
        if (state.value.name.isNotBlank()) {
            viewModelScope.launch {
                observeInternetConnection.observe().collectLatest { available ->
                    communication.showLoading()
                    if (!available) {
                        val error = provideResources.string(
                            R.string.error_waiting_internet_connection
                        )
                        communication.showNoInternetConnectionWarning(error)
                    }
                }
            }
            viewModelScope.launch {
                interactor.createProfile(state.value.userInitial()).map(createProfileResultMapper)
            }
        } else {
            viewModelScope.launch {
                communication.launchEmptyNameWarning()
                delay(300L)
                communication.stopEmptyNameWarning()
            }
        }
    }

    private companion object {
        const val MAX_NAME_LENGTH = 15
    }
}