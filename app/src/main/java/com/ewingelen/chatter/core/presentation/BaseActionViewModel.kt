package com.ewingelen.chatter.core.presentation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 29.04.2023.
 */
abstract class BaseActionViewModel<S, A>(defaultState: S) :
    BaseViewModel<S>(defaultState) {

    abstract fun handleAction(action: A)
}

interface Action<T> {

    fun handle(handle: T)
}