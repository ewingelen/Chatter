package com.ewingelen.chatter.main.presentation

import androidx.lifecycle.viewModelScope
import com.ewingelen.chatter.auth.core.domain.AuthorizedStatusRepository
import com.ewingelen.chatter.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AuthorizedStatusRepository.Check
) : BaseViewModel<MainState>(defaultState = MainState()) {

    init {
        viewModelScope.launch {
            Timber.d(repository.checkUserAuthorized().toString())
            updateState(
                state.value.copy(
                    userAuthorized = repository.checkUserAuthorized(),
                    loading = false
                )
            )
        }
    }
}