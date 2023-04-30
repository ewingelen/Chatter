package com.ewingelen.chatter.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ewingelen.chatter.auth.sendAuthCode.presentation.confirmationCodeScreen
import com.ewingelen.chatter.auth.sendAuthCode.presentation.navigateToConfirmationCode
import com.ewingelen.chatter.auth.verifyPhone.presentation.navigateToPhoneNumber
import com.ewingelen.chatter.auth.verifyPhone.presentation.phoneNumberScreen
import com.ewingelen.chatter.main.presentation.mainScreen
import com.ewingelen.chatter.main.presentation.navigateToMain
import com.ewingelen.chatter.onboarding.presentation.OnBoardingScreen

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ON_BOARDING_SCREEN_ROUTE) {
        onBoardingScreen(navigateToAuth = navController::navigateToPhoneNumber)
        phoneNumberScreen(
            navigateToCode = { verificationId ->
                navController.navigateToConfirmationCode()
            },
            navigateToMain = navController::navigateToMain
        )
        confirmationCodeScreen(navigateToMain = navController::navigateToMain)
        mainScreen()
    }
}

private const val ON_BOARDING_SCREEN_ROUTE = "auth"

private fun NavGraphBuilder.onBoardingScreen(navigateToAuth: () -> Unit) {
    composable(ON_BOARDING_SCREEN_ROUTE) {
        OnBoardingScreen(navigateToAuth = navigateToAuth)
    }
}