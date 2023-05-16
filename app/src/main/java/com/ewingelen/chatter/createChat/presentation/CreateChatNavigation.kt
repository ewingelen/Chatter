package com.ewingelen.chatter.createChat.presentation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
private const val ROUTE = "create_chat"

fun NavGraphBuilder.createChatScreen(navigateUp: () -> Unit) {
    composable(ROUTE) {
        val viewModel: CreateChatViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        CreateChatScreen(
            state = state,
            handleAction = viewModel::handleAction,
            navigateUp = navigateUp
        )
    }
}

fun NavController.navigateToCreateChat() {
    navigate(ROUTE)
}