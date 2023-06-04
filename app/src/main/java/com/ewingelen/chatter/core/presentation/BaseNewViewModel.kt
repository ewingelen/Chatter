package com.ewingelen.chatter.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseNewViewModel<S, A, E>() : ViewModel() {

    abstract fun handleAction(action: A)

    abstract fun state(): StateFlow<S>

    abstract fun effect(): Flow<E>
}