package com.ewingelen.chatter.core.presentation

import androidx.lifecycle.ViewModel

abstract class BaseNewViewModel<S : Any, A: Any>(
    private val communication: ObserveState<S>
) : ViewModel(), ObserveState<S> {

    abstract fun handleAction(action: A)

    override fun state() = communication.state()
}

interface Action<T : Any> {

    fun handle(handle: T)
}