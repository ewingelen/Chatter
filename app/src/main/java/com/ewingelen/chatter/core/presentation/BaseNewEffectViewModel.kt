package com.ewingelen.chatter.core.presentation

abstract class BaseNewEffectViewModel<S : Any, A : Any, E : Any>(
    private val communication: ObserveUi<S, E>
) : BaseNewViewModel<S, A>(communication), ObserveEffect<E> {

    override fun effect() = communication.effect()
}