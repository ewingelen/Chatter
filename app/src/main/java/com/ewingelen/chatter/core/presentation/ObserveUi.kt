package com.ewingelen.chatter.core.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ObserveUi<S, E> {

    fun state(): StateFlow<S>

    fun effect(): Flow<E>
}