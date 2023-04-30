package com.ewingelen.chatter.auth.sendAuthCode.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
private const val ROUTE = "code"

fun NavGraphBuilder.confirmationCodeScreen(navigateToMain: () -> Unit) {
    composable(ROUTE) {
        ConfirmationCodeScreen(navigateToMain = navigateToMain)
    }
}

fun NavController.navigateToConfirmationCode() {
    navigate(ROUTE)
}