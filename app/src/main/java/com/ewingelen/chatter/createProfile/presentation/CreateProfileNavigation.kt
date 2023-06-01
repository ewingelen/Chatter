package com.ewingelen.chatter.createProfile.presentation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "create_profile"

fun NavGraphBuilder.createProfileScreen() {
    composable(ROUTE) {
        val viewModel: CreateProfileViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        CreateProfileScreen(state = state, handleAction = viewModel::handleAction)
    }
}

fun NavController.navigateToCreateProfile() {
    navigate(ROUTE)
}