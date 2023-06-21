package com.ewingelen.chatter.chat.presentation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ewingelen.chatter.core.presentation.navigation.makeDestinationRouteWithArgs
import com.ewingelen.chatter.core.presentation.navigation.makeNavigationRouteWithArgs

private const val ROUTE = "chat"
private const val CHAT_ID_ARG = "chat_id"

class ChatNavigationArg(val chatId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        checkNotNull(savedStateHandle[CHAT_ID_ARG]) as String
    )
}

fun NavGraphBuilder.chatScreen(
    navigateToCall: () -> Unit,
    navigateUp: () -> Unit
) {
    val route = makeDestinationRouteWithArgs(ROUTE, CHAT_ID_ARG)
    composable(route) {
        val viewModel: ChatViewModel = hiltViewModel()
        val state by viewModel.state().collectAsStateWithLifecycle()
        ChatScreen(
            state = state,
            effect = viewModel.effect(),
            handleAction = viewModel::handleAction,
            navigateToCall = navigateToCall,
            navigateUp = navigateUp
        )
    }
}

fun NavController.navigateToChat(contactId: String) {
    val route = makeNavigationRouteWithArgs(ROUTE, contactId)
    navigate(route)
}