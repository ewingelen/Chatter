package com.ewingelen.chatter.core.presentation

abstract class BaseActionViewModel<S, A>(defaultState: S) : BaseViewModel<S>(defaultState) {

    abstract fun handleAction(action: A)
}