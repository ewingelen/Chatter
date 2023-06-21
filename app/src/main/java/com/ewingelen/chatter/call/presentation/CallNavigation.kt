package com.ewingelen.chatter.call.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "call"

fun NavGraphBuilder.callScreen(navigateUp: () -> Unit) {
    composable(ROUTE) {
//        val channelName = intent.getStringExtra("ChannelName")
//        val userRole = intent.getStringExtra("UserRole")
//    if (channelName != null && userRole != null) {
        val viewModel: CallViewModel = hiltViewModel()
        CallScreen(
            channelName = "channelName",
            userRole = "userRole",
            changeUserPresence = { presence ->
                viewModel.changeUserPresence(presence)
            },
            navigateUp = navigateUp
        )
//    }
    }
}

fun NavController.navigateToCall() {
    navigate(ROUTE)
}