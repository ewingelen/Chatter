package com.ewingelen.chatter.auth.core.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.ewingelen.chatter.auth.confirmCode.presentation.ConfirmCodeArgs
import com.ewingelen.chatter.auth.confirmCode.presentation.confirmCodeScreen
import com.ewingelen.chatter.auth.confirmCode.presentation.navigateToConfirmCode
import com.ewingelen.chatter.auth.verifyPhone.presentation.navigateToPhoneNumber
import com.ewingelen.chatter.auth.verifyPhone.presentation.phoneNumberScreen
import com.ewingelen.chatter.core.presentation.navigation.navigateToChats
import com.ewingelen.chatter.onBoarding.presentation.OnBoardingScreen

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 06.05.2023.
 */
fun NavGraphBuilder.authGraph(
    navController: NavController,
    route: String,
    verifyPhoneNumber: (VerifyPhoneNumber) -> Unit
) {
    navigation(startDestination = ON_BOARDING_SCREEN_ROUTE, route = route) {
        val popUpToTopNavOptions by lazy {
            navOptions {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        }
        val navigateToChats = {
            navController.navigateToChats(navOptions = popUpToTopNavOptions)
        }
        onBoardingScreen(navigateToPhoneNumber = navController::navigateToPhoneNumber)
        phoneNumberScreen(
            navigateToConfirmCode = { verificationId, phoneNumber ->
                val args = ConfirmCodeArgs(verificationId, phoneNumber)
                navController.navigateToConfirmCode(args, popUpToTopNavOptions)
            },
            navigateToChats = navigateToChats,
            verifyPhoneNumber = verifyPhoneNumber
        )
        confirmCodeScreen(
            navigateToChats = navigateToChats,
            verifyPhoneNumber = verifyPhoneNumber
        )
    }
}

private const val ON_BOARDING_SCREEN_ROUTE = "on_boarding"

private fun NavGraphBuilder.onBoardingScreen(navigateToPhoneNumber: () -> Unit) {
    composable(ON_BOARDING_SCREEN_ROUTE) {
        OnBoardingScreen(navigateToPhoneNumber = navigateToPhoneNumber)
    }
}