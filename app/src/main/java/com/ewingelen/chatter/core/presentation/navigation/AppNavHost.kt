package com.ewingelen.chatter.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ewingelen.chatter.auth.confirmationCodeScreen
import com.ewingelen.chatter.auth.navigateToConfirmationCode
import com.ewingelen.chatter.auth.navigateToPhoneNumber
import com.ewingelen.chatter.auth.phoneNumberScreen
import com.ewingelen.chatter.onboarding.OnBoardingScreen

private const val ON_BOARDING_SCREEN_ROUTE = "auth"

private fun NavGraphBuilder.onBoardingScreen(navigateToAuth: () -> Unit) {
    composable(ON_BOARDING_SCREEN_ROUTE) {
        OnBoardingScreen(navigateToAuth = navigateToAuth)
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ON_BOARDING_SCREEN_ROUTE) {
        onBoardingScreen(navigateToAuth = navController::navigateToPhoneNumber)
        phoneNumberScreen(navigateToCode = navController::navigateToConfirmationCode)
        confirmationCodeScreen()
    }
}