package com.ewingelen.chatter.auth.core.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.ewingelen.chatter.auth.confirmCode.presentation.confirmCodeScreen
import com.ewingelen.chatter.auth.confirmCode.presentation.navigateToConfirmCode
import com.ewingelen.chatter.auth.createProfile.presentation.createProfileScreen
import com.ewingelen.chatter.auth.createProfile.presentation.navigateToCreateProfile
import com.ewingelen.chatter.auth.verifyPhone.presentation.navigateToPhoneNumber
import com.ewingelen.chatter.auth.verifyPhone.presentation.phoneNumberScreen
import com.ewingelen.chatter.core.presentation.navigation.navigateToAppSections
import com.ewingelen.chatter.onBoarding.presentation.OnBoardingScreen

fun NavGraphBuilder.authGraph(
    navController: NavController,
    route: String,
    verifyPhoneNumber: (VerifyPhoneNumber) -> Unit
) {
    navigation(startDestination = ON_BOARDING_ROUTE, route = route) {
        val popUpToTopNavOptions by lazy {
            navOptions {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        }
        val navigateToAppSections = {
            navController.navigateToAppSections(navOptions = popUpToTopNavOptions)
        }
        onBoardingScreen(navigateToPhoneNumber = navController::navigateToPhoneNumber)
        phoneNumberScreen(
            navigateToConfirmCode = { args ->
                navController.navigateToConfirmCode(args, popUpToTopNavOptions)
            },
            navigateToAppSections = navigateToAppSections,
            verifyPhoneNumber = verifyPhoneNumber
        )
        confirmCodeScreen(
            navigateToCreateProfile = {
                navController.navigateToCreateProfile(popUpToTopNavOptions)
            },
            navigateToAppSections = navigateToAppSections,
            verifyPhoneNumber = verifyPhoneNumber
        )
        createProfileScreen(navigateToChats = navigateToAppSections)
    }
}

private const val ON_BOARDING_ROUTE = "on_boarding"

private fun NavGraphBuilder.onBoardingScreen(navigateToPhoneNumber: () -> Unit) {
    composable(ON_BOARDING_ROUTE) {
        OnBoardingScreen(navigateToPhoneNumber = navigateToPhoneNumber)
    }
}