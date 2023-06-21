package com.ewingelen.chatter.core.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ewingelen.chatter.appSections.presentation.AppSectionsScreen
import com.ewingelen.chatter.call.presentation.callScreen
import com.ewingelen.chatter.call.presentation.navigateToCall
import com.ewingelen.chatter.chat.presentation.chatScreen
import com.ewingelen.chatter.chat.presentation.navigateToChat
import com.ewingelen.chatter.chats.create.presentation.CreateChatViewModel
import com.ewingelen.chatter.chats.main.presentation.ChatsViewModel
import com.ewingelen.chatter.profile.presentation.ProfileViewModel
import com.ewingelen.settings.presentation.SettingsViewModel

fun NavGraphBuilder.mainGraph(
    navController: NavController,
    route: String,
    showSnackbar: (String) -> Unit
) {
    navigation(startDestination = APP_SECTIONS_ROUTE, route = route) {
        appSectionsScreen(
            showSnackbar = showSnackbar,
            navigateToChat = { chatId ->
                navController.navigateToChat(chatId)
            },
            navigateToOnBoarding = {
                navController.navigate(navController.graph.startDestinationRoute!!) {
                    popUpTo(navController.graph.startDestinationId)
                }
            }
        )
        chatScreen(
            navigateToCall = navController::navigateToCall,
            navigateUp = navController::navigateUp
        )
        callScreen(navigateUp = navController::navigateUp)
    }
}

private const val APP_SECTIONS_ROUTE = "app_sections"

fun NavGraphBuilder.appSectionsScreen(
    navigateToChat: (chatId: String) -> Unit,
    navigateToOnBoarding: () -> Unit,
    showSnackbar: (String) -> Unit,
) {
    composable(APP_SECTIONS_ROUTE) {
        val chatsViewModel: ChatsViewModel = hiltViewModel()
        val chatsState by chatsViewModel.state().collectAsStateWithLifecycle()
        val createChatViewModel: CreateChatViewModel = hiltViewModel()
        val createChatState by createChatViewModel.state().collectAsStateWithLifecycle()
        val profileViewModel: ProfileViewModel = hiltViewModel()
        val profileState by profileViewModel.state().collectAsStateWithLifecycle()
        val settingsViewModel: SettingsViewModel = hiltViewModel()
        val settingsState by settingsViewModel.state.collectAsStateWithLifecycle()
        AppSectionsScreen(
            chatsState = chatsState,
            createChatsState = createChatState,
            createChatEffect = createChatViewModel.effect(),
            handleCreateChatAction = createChatViewModel::handleAction,
            handleChatsAction = chatsViewModel::handleAction,
            profileState = profileState,
            handleProfileAction = profileViewModel::handleAction,
            settingsState = settingsState,
            handleSettingsAction = settingsViewModel::selectLanguage,
            showSnackbar = showSnackbar,
            navigateToChat = navigateToChat,
            navigateToOnBoarding = navigateToOnBoarding
        )
    }
}

fun NavController.navigateToAppSections(navOptions: NavOptions) {
    navigate(APP_SECTIONS_ROUTE, navOptions)
}

