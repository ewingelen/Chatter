package com.ewingelen.chatter.call.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewingelen.chatter.call.domain.UserPresenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallViewModel @Inject constructor(
    private val repository: UserPresenceRepository
): ViewModel() {

    fun changeUserPresence(presence: Boolean) {
        viewModelScope.launch {
            repository.changeUserPresence(presence)
        }
    }
}