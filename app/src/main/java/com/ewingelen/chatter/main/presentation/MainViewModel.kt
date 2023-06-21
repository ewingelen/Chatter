package com.ewingelen.chatter.main.presentation

import androidx.lifecycle.viewModelScope
import com.ewingelen.chatter.auth.core.domain.AuthorizedStatusRepository
import com.ewingelen.chatter.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AuthorizedStatusRepository.Read,
) : BaseViewModel<MainState>(defaultState = MainState()) {

    init {
        viewModelScope.launch {
            val userAuthorized = repository.checkUserAuthorized()
            updateState(state.value.copy(userAuthorized = userAuthorized))
            //TODO: fix and remove delay
            delay(200L)
            updateState(state.value.copy(loading = false))
        }
    }
}