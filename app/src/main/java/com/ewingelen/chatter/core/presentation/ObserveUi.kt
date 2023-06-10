package com.ewingelen.chatter.core.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ObserveState<S> {

    fun state(): StateFlow<S>
}

interface ObserveEffect<E> {

    fun effect(): Flow<E>
}

interface ObserveUi<S, E>: ObserveState<S>, ObserveEffect<E>