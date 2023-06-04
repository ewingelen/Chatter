package com.ewingelen.chatter.main.presentation

import androidx.lifecycle.viewModelScope
import com.ewingelen.chatter.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkUserAuthorized: CheckUserAuthorized
) : BaseViewModel<MainState>(defaultState = MainState()) {

    init {
        viewModelScope.launch {
            updateState(
                state.value.copy(
                    userAuthorized = checkUserAuthorized.check(),
                    loading = false
                )
            )
        }
    }
}