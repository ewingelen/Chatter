package com.ewingelen.chatter.main.presentation

data class MainState(
    val loading: Boolean = true,
    val userAuthorized: Boolean = false
)