package com.ewingelen.chatter.chat.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
private const val ROUTE = "chat"

fun NavGraphBuilder.chatScreen(
    navigateUp: () -> Unit
) {
    composable(ROUTE) {
        ChatScreen(navigateUp = navigateUp)
    }
}

fun NavController.navigateToChat() {
    navigate(ROUTE)
}