package com.ewingelen.chatter.core.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ewingelen.chatter.chat.presentation.chatScreen
import com.ewingelen.chatter.chat.presentation.navigateToChat
import com.ewingelen.chatter.chats.presentation.ChatsScreen
import com.ewingelen.chatter.chats.presentation.ChatsViewModel
import com.ewingelen.chatter.createChat.presentation.createChatScreen
import com.ewingelen.chatter.createChat.presentation.navigateToCreateChat

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 07.05.2023.
 */
fun NavGraphBuilder.mainGraph(navController: NavController, route: String) {
    navigation(startDestination = CHATS_SCREEN_ROUTE, route = route) {
        chatsScreen(
            navigateToCreateChat = navController::navigateToCreateChat,
            navigateToChat = navController::navigateToChat
        )
        createChatScreen(navigateUp = navController::navigateUp)
        chatScreen(navigateUp = navController::navigateUp)
    }
}

private const val CHATS_SCREEN_ROUTE = "chats"

private fun NavGraphBuilder.chatsScreen(
    navigateToCreateChat: () -> Unit,
    navigateToChat: () -> Unit
) {
    composable(CHATS_SCREEN_ROUTE) {
        val viewModel: ChatsViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        ChatsScreen(
            state = state,
            navigateToCreateChat = navigateToCreateChat,
            navigateToChat = navigateToChat
        )
    }
}

fun NavController.navigateToChats(navOptions: NavOptions) {
    navigate(CHATS_SCREEN_ROUTE, navOptions)
}

