package com.ewingelen.chatter.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 13.05.2023.
 */
abstract class BaseViewModel<S : State>(defaultState: S) : ViewModel() {

    private val _state = MutableStateFlow(defaultState)
    val state = _state.asStateFlow()

    protected fun updateState(state: S) {
        _state.value = state
    }
}

interface State