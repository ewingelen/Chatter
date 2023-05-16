package com.ewingelen.chatter.main.presentation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 13.05.2023.
 */
data class MainState(
    val loading: Boolean = true,
    val userAuthorized: Boolean = false
)