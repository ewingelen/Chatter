package com.ewingelen.chatter.chat.presentation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val ROUTE = "chat"

fun NavGraphBuilder.chatScreen(
    navigateUp: () -> Unit
) {
    composable(ROUTE) {
        val viewModel: ChatViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        ChatScreen(
            state = state,
            handleAction = viewModel::handleAction,
            navigateUp = navigateUp
        )
    }
}

fun NavController.navigateToChat() {
    navigate(ROUTE)
}