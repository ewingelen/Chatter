package com.ewingelen.chatter.auth.verifyPhone.presentation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
private const val ROUTE = "phone_number"

fun NavGraphBuilder.phoneNumberScreen(
    navigateToCode: (String) -> Unit,
    navigateToMain: () -> Unit
) {
    composable(ROUTE) {
        val viewModel: PhoneNumberViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        PhoneNumberScreen(
            state = state,
            effect = viewModel.effect,
            handleAction = viewModel::handleAction,
            navigateToCode = navigateToCode,
            navigateToMain = navigateToMain
        )
    }
}

fun NavController.navigateToPhoneNumber() {
    navigate(ROUTE)
}