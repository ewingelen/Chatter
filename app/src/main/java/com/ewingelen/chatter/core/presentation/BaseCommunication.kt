package com.ewingelen.chatter.core.presentation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseCommunication<S, E>(defaultState: S) {

    protected val state = MutableStateFlow(defaultState)

    protected val effect = Channel<E>()
}