package com.ewingelen.chatter.auth.createProfile.presentation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

private const val ROUTE = "create_profile"

fun NavGraphBuilder.createProfileScreen(navigateToChats: () -> Unit) {
    composable(ROUTE) {
        val viewModel: CreateProfileViewModel = hiltViewModel()
        val state by viewModel.state().collectAsStateWithLifecycle()
        CreateProfileScreen(
            state = state,
            effect = viewModel.effect(),
            handleAction = viewModel::handleAction,
            navigateToChats = navigateToChats
        )
    }
}

fun NavController.navigateToCreateProfile(navOptions: NavOptions) {
    navigate(ROUTE, navOptions)
}