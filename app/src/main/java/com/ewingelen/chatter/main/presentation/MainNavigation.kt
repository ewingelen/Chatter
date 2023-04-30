package com.ewingelen.chatter.main.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
private const val ROUTE = "main"

fun NavGraphBuilder.mainScreen() {
    composable(ROUTE) {
        MainScreen()
    }
}

fun NavController.navigateToMain() {
    navigate(ROUTE)
}