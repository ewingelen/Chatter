package com.ewingelen.chatter.core.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseCommunication<S>(defaultState: S) : ObserveState<S> {

    protected val state = MutableStateFlow(defaultState)

    protected fun updateState(newState: S) {
        state.update { newState }
    }

    override fun state() = state.asStateFlow()
}