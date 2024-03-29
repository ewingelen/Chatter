package com.ewingelen.chatter.auth.verifyPhone.presentation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ewingelen.chatter.auth.confirmCode.presentation.ConfirmCodeArgs
import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber

private const val ROUTE = "phone_number"

fun NavGraphBuilder.phoneNumberScreen(
    navigateToConfirmCode: (ConfirmCodeArgs) -> Unit,
    navigateToAppSections: () -> Unit,
    verifyPhoneNumber: (VerifyPhoneNumber) -> Unit
) {
    composable(ROUTE) {
        val viewModel: PhoneNumberViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        PhoneNumberScreen(
            state = state,
            effect = viewModel.effect,
            handleAction = viewModel::handleAction,
            verifyPhoneNumber = verifyPhoneNumber,
            navigateToConfirmCode = navigateToConfirmCode,
            navigateToAppSections = navigateToAppSections
        )
    }
}

fun NavController.navigateToPhoneNumber() {
    navigate(ROUTE)
}