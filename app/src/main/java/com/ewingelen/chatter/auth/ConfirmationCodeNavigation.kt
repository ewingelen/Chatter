package com.ewingelen.chatter.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * Created by Artem Skorik email(artem.newage@outlook.com) on 05.04.2023.
 */
private const val CODE_ROUTE = "code"

fun NavGraphBuilder.confirmationCodeScreen() {
    composable(CODE_ROUTE) {
        ConfirmationCodeScreen()
    }
}

fun NavController.navigateToConfirmationCode() {
    navigate(CODE_ROUTE)
}