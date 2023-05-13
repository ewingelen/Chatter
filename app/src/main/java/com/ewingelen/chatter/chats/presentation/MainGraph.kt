package com.ewingelen.chatter.chats.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 07.05.2023.
 */
fun NavGraphBuilder.mainGraph(navController: NavController, route: String) {
    navigation(startDestination = CHATS_SCREEN_ROUTE, route = route) {
        chatsScreen()
    }
}

private const val CHATS_SCREEN_ROUTE = "chats"

private fun NavGraphBuilder.chatsScreen() {
    composable(CHATS_SCREEN_ROUTE) {
        ChatsScreen()
    }
}

fun NavController.navigateToChats(navOptions: NavOptions) {
    navigate(CHATS_SCREEN_ROUTE, navOptions)
}

