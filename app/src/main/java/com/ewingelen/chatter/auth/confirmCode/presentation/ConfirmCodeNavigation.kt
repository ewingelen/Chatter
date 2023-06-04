package com.ewingelen.chatter.auth.confirmCode.presentation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.core.presentation.navigation.makeDestinationRouteWithArgs
import com.ewingelen.chatter.core.presentation.navigation.makeNavigationRouteWithArgs


private const val ROUTE = "code"
private const val VERIFICATION_ID_ARG = "verificationId"
private const val PHONE_NUMBER_ARG = "phoneNumber"

class ConfirmCodeArgs(val verificationId: String, val phoneNumber: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        checkNotNull(savedStateHandle[VERIFICATION_ID_ARG]) as String,
        checkNotNull(savedStateHandle[PHONE_NUMBER_ARG]) as String
    )
}

fun NavGraphBuilder.confirmCodeScreen(
    navigateToCreateProfile: () -> Unit,
    navigateToChats: () -> Unit,
    verifyPhoneNumber: (VerifyPhoneNumber) -> Unit
) {
    val route = makeDestinationRouteWithArgs(ROUTE, VERIFICATION_ID_ARG, PHONE_NUMBER_ARG)
    composable(route) {
        val viewModel: ConfirmCodeViewModel = hiltViewModel()
        val state by viewModel.state().collectAsStateWithLifecycle()
        ConfirmCodeScreen(
            state = state,
            effect = viewModel.effect(),
            handleAction = viewModel::handleAction,
            navigateToCreateProfile = navigateToCreateProfile,
            navigateToChats = navigateToChats,
            verifyPhoneNumber = verifyPhoneNumber,
        )
    }
}

fun NavController.navigateToConfirmCode(args: ConfirmCodeArgs, navOptions: NavOptions) {
    val route = makeNavigationRouteWithArgs(ROUTE, args.verificationId, args.phoneNumber)
    navigate(route, navOptions)
}