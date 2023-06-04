package com.ewingelen.chatter.createChat.presentation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val ROUTE = "create_chat"

fun NavGraphBuilder.createChatScreen(
    navigateUp: () -> Unit,
    showSnackbar: (String) -> Unit
) {
    composable(ROUTE) {
        val viewModel: CreateChatViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        CreateChatScreen(
            state = state,
            handleAction = viewModel::handleAction,
            effect = viewModel.effect,
            showSnackbar = showSnackbar,
            navigateUp = navigateUp
        )
    }
}

fun NavController.navigateToCreateChat() {
    navigate(ROUTE)
}