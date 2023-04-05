package com.ewingelen.chatter.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val PHONE_NUMBER_ROUTE = "phone_number"

fun NavGraphBuilder.phoneNumberScreen(navigateToCode: () -> Unit) {
    composable(PHONE_NUMBER_ROUTE) {
        AuthScreen(navigateToCode = navigateToCode)
    }
}

fun NavController.navigateToPhoneNumber() {
    navigate(PHONE_NUMBER_ROUTE)
}