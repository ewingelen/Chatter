package com.ewingelen.chatter.chats.main.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import com.ewingelen.chatter.R
import com.ewingelen.chatter.chats.create.presentation.compontents.CreateChatBottomSheet
import com.ewingelen.chatter.chats.create.presentation.contract.CreateChatAction
import com.ewingelen.chatter.chats.create.presentation.contract.CreateChatEffect
import com.ewingelen.chatter.chats.create.presentation.contract.CreateChatState
import com.ewingelen.chatter.chats.main.presentation.compontents.ChatsList
import com.ewingelen.chatter.chats.main.presentation.compontents.EmptyChatsSection
import com.ewingelen.chatter.chats.main.presentation.contract.ChatsAction
import com.ewingelen.chatter.chats.main.presentation.contract.ChatsState
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsScreen(
    state: ChatsState,
    createChatState: CreateChatState.Base,
    createChatEffect: Flow<CreateChatEffect>,
    handleCreateChatAction: (CreateChatAction) -> Unit,
    handleChatsAction: (ChatsAction) -> Unit,
    changeTopBarTitle: (String) -> Unit,
    navigateToChat: (String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val createChatBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var createNewChatBottomSheetVisible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = state.topBarTitle) {
        changeTopBarTitle(state.topBarTitle)
    }

    Column {
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.loading -> {
                    CircularProgressIndicator(
                        strokeCap = StrokeCap.Round,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                state.chats.isNotEmpty() -> {
                    ChatsList(
                        chats = state.chats,
                        navigateToChat = navigateToChat,
                        onDeleteChat = {
                            handleChatsAction(ChatsAction.DeleteChat(it))
                        }
                    )
                }

                else -> {
                    EmptyChatsSection(modifier = Modifier.align(Alignment.Center))
                }
            }

            FloatingActionButton(
                onClick = {
                    createNewChatBottomSheetVisible = true
                    coroutineScope.launch {
                        createChatBottomSheetState.show()
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(SpacingNormal100),
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(id = R.string.accessibility_add_contact)
                )
            }
        }
    }

    CreateChatBottomSheet(
        state = createChatState,
        effect = createChatEffect,
        handleAction = handleCreateChatAction,
        sheetState = createChatBottomSheetState,
        visible = createNewChatBottomSheetVisible,
        onDismissRequest = {
            createNewChatBottomSheetVisible = false
            coroutineScope.launch {
                createChatBottomSheetState.hide()
            }
        }
    )
}

@ScreenPreview
@Composable
private fun ChatsScreenPreview() {
    ChatterThemeWithSurface {
        ChatsScreen(
            state = ChatsState(),
            createChatState = CreateChatState.Base(),
            createChatEffect = flow {},
            handleCreateChatAction = {},
            handleChatsAction = {},
            changeTopBarTitle = {},
            navigateToChat = {}
        )
    }
}