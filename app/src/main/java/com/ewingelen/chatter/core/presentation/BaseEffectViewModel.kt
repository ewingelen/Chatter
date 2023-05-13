package com.ewingelen.chatter.core.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 30.04.2023.
 */
abstract class BaseEffectViewModel<S : State, A : HandleAction, E : HandleEffect>(defaultState: S) :
    BaseActionViewModel<S, A>(defaultState) {

    private val mutableEffect = Channel<Effect<E>>()
    val effect = mutableEffect.receiveAsFlow()

    fun sendEffect(effect: Effect<E>) {
        viewModelScope.launch {
            mutableEffect.send(effect)
        }
    }

    fun sendEffect(coroutineScope: CoroutineScope, effect: Effect<E>) {
        coroutineScope.launch {
            mutableEffect.send(effect)
        }
    }
}

interface Effect<T : HandleEffect> {

    fun handle(handleEffect: T)
}

interface HandleEffect