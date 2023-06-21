package com.ewingelen.chatter.appSections.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.R
import com.ewingelen.chatter.chats.create.presentation.contract.CreateChatAction
import com.ewingelen.chatter.chats.create.presentation.contract.CreateChatEffect
import com.ewingelen.chatter.chats.create.presentation.contract.CreateChatState
import com.ewingelen.chatter.chats.main.presentation.ChatsScreen
import com.ewingelen.chatter.chats.main.presentation.compontents.AppSectionsTopAppBar
import com.ewingelen.chatter.chats.main.presentation.contract.ChatsAction
import com.ewingelen.chatter.chats.main.presentation.contract.ChatsState
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface
import com.ewingelen.chatter.profile.presentation.ProfileScreen
import com.ewingelen.chatter.profile.presentation.contract.ProfileAction
import com.ewingelen.chatter.profile.presentation.contract.ProfileState
import com.ewingelen.settings.presentation.SettingsScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AppSectionsScreen(
    chatsState: ChatsState,
    createChatsState: CreateChatState.Base,
    createChatEffect: Flow<CreateChatEffect>,
    handleCreateChatAction: (CreateChatAction) -> Unit,
    handleChatsAction: (ChatsAction) -> Unit,
    profileState: ProfileState,
    handleProfileAction: (ProfileAction) -> Unit,
    settingsState: Boolean,
    handleSettingsAction: (Boolean) -> Unit,
    showSnackbar: (String) -> Unit,
    navigateToChat: (String) -> Unit,
    navigateToOnBoarding: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val topAppBarScrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val appNameLabel = stringResource(id = R.string.app_name)
    val profileTopAppBarTitle = stringResource(id = R.string.top_bar_title_my_profile)
    val settingsTopAppBarTitle = stringResource(id = R.string.top_bar_title_settings)
    var topAppBarTitle by remember { mutableStateOf(appNameLabel) }

    LaunchedEffect(key1 = pagerState.currentPage) {
        when (pagerState.currentPage) {
            0 -> topAppBarTitle = appNameLabel
            1 -> topAppBarTitle = profileTopAppBarTitle
            2 -> topAppBarTitle = settingsTopAppBarTitle
        }
    }

    Scaffold(
        topBar = {
            AppSectionsTopAppBar(
                title = topAppBarTitle,
                selectedTab = pagerState.currentPage,
                onTabSelect = { selectedTab ->
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(selectedTab)
                    }
                },
                scrollBehavior = topAppBarScrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
    ) { padding ->
        HorizontalPager(
            state = pagerState,
            pageCount = 3,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(padding),
        ) { page ->
            when (page) {
                0 -> {
                    ChatsScreen(
                        state = chatsState,
                        createChatEffect = createChatEffect,
                        createChatState = createChatsState,
                        handleCreateChatAction = handleCreateChatAction,
                        handleChatsAction = handleChatsAction,
                        changeTopBarTitle = { topBarTitle ->
                            if(pagerState.currentPage == 0) {
                                topAppBarTitle = topBarTitle
                            }
                        },
                        navigateToChat = navigateToChat
                    )
                }

                1 -> {
                    ProfileScreen(
                        state = profileState,
                        handleAction = handleProfileAction,
                        showSnackbar = showSnackbar,
                        navigateToOnBoarding = navigateToOnBoarding,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                2 -> {
                    SettingsScreen(
                        englishSelected = settingsState,
                        selectLanguage = handleSettingsAction
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AppSectionsScreenPreview() {
    ChatterThemeWithSurface {
        AppSectionsScreen(
            chatsState = ChatsState(),
            createChatsState = CreateChatState.Base(),
            createChatEffect = flow {},
            handleCreateChatAction = {},
            handleChatsAction = {},
            profileState = ProfileState(),
            handleProfileAction = {},
            settingsState = true,
            handleSettingsAction = {},
            showSnackbar = {},
            navigateToChat = {},
            navigateToOnBoarding = {}
        )
    }
}