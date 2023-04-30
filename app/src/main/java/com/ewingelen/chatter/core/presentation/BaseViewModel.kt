package com.ewingelen.chatter.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 29.04.2023.
 */
abstract class BaseViewModel<S : State, A : HandleAction> : ViewModel() {

    var state = MutableStateFlow(this.defaultState())
        private set

    protected abstract fun defaultState(): S

    abstract fun handleAction(action: Action<A>)

    protected fun updateState(state: S) {
        this.state.value = state
    }
}

interface State

interface Action<T : HandleAction> {

    fun handle(handleAction: T)
}

interface HandleAction