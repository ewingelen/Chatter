package com.ewingelen.chatter.createProfile.presentation

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.ewingelen.chatter.core.presentation.BaseEffectViewModel
import com.ewingelen.chatter.createProfile.domain.CreateProfileInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProfileViewModel @Inject constructor(
    private val interactor: CreateProfileInteractor
) : BaseEffectViewModel<CreateProfileState, CreateProfileAction, CreateProfileEffect>(CreateProfileState()),
    HandleCreateProfileAction {

    override fun handleAction(action: CreateProfileAction) {
        action.handle(this)
    }

    override fun changeName(newName: String) {
        updateState(state.value.copy(name = newName))
        if (state.value.errorVisible) {
            updateState(state.value.copy(errorVisible = false))
        }
    }

    override fun addPhoto(newPhoto: Uri) {
        updateState(state.value.copy(photo = newPhoto))
    }

    override fun createProfile() {
        if (state.value.name.isNotBlank()) {
            viewModelScope.launch {
                interactor.createProfile(state.value.name)
                if (state.value.photo != Uri.EMPTY) {
                    interactor.addPhoto(state.value.photo)
                }
            }
        } else {
            updateState(state.value.copy(errorVisible = true))
        }
    }
}