package com.ewingelen.chatter.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<S>(defaultState: S) : ViewModel() {

    private val _state = MutableStateFlow(defaultState)
    val state = _state.asStateFlow()

    protected fun updateState(state: S) {
        _state.value = state
    }
}