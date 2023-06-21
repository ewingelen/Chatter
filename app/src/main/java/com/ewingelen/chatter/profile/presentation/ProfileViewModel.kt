package com.ewingelen.chatter.profile.presentation

import androidx.lifecycle.viewModelScope
import com.ewingelen.chatter.core.presentation.BaseNewViewModel
import com.ewingelen.chatter.profile.domain.ProfileRepository
import com.ewingelen.chatter.profile.presentation.contract.HandleProfileAction
import com.ewingelen.chatter.profile.presentation.contract.ProfileAction
import com.ewingelen.chatter.profile.presentation.contract.ProfileCommunication
import com.ewingelen.chatter.profile.presentation.contract.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val communication: ProfileCommunication
) : BaseNewViewModel<ProfileState, ProfileAction>(communication), HandleProfileAction {

    init {
        viewModelScope.launch {
            val user = repository.fetchInfo()
            communication.showData(user)
        }
    }

    override fun handleAction(action: ProfileAction) {
        action.handle(this)
    }

    override fun signOut() {
        viewModelScope.launch {
            repository.signOut()
        }
    }
}