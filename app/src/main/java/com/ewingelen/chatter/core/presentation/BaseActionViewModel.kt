package com.ewingelen.chatter.core.presentation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 29.04.2023.
 */
abstract class BaseActionViewModel<S : State, A : HandleAction>(defaultState: S) :
    BaseViewModel<S>(defaultState) {

    abstract fun handleAction(action: Action<A>)
}

interface Action<T : HandleAction> {

    fun handle(handleAction: T)
}

interface HandleAction