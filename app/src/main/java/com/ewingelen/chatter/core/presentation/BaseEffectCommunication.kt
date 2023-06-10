package com.ewingelen.chatter.core.presentation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseEffectCommunication<S, E>(defaultState: S) : BaseCommunication<S>(defaultState), ObserveEffect<E> {

    protected val effect = Channel<E>()

    protected fun sendEffect(effect: E) {
        this.effect.trySend(effect)
    }

    override fun effect() = effect.receiveAsFlow()
}