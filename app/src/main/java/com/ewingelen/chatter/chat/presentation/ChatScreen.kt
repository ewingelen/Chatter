package com.ewingelen.chatter.chat.presentation

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import com.ewingelen.chatter.chat.presentation.components.ChatTopAppBar
import com.ewingelen.chatter.chat.presentation.components.FullScreenImage
import com.ewingelen.chatter.chat.presentation.components.MessagesList
import com.ewingelen.chatter.chat.presentation.components.NoMessagesCard
import com.ewingelen.chatter.chat.presentation.components.SendSection
import com.ewingelen.chatter.chat.presentation.contract.ChatAction
import com.ewingelen.chatter.chat.presentation.contract.ChatState
import com.ewingelen.chatter.core.domain.model.Message
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

@Composable
fun ChatScreen(
    state: ChatState,
    effect: Flow<Unit>,
    handleAction: (ChatAction) -> Unit,
    navigateToCall: () -> Unit,
    navigateUp: () -> Unit
) {
    var fullImagePath by remember { mutableStateOf("") }
    val messagesScrollState = rememberLazyListState()

    LaunchedEffect(key1 = state.loading) {
        if (!state.loading) {
            if (state.chat.messages.isNotEmpty()) {
                messagesScrollState.scrollToItem(state.chat.messages.lastIndex)
            }
            effect.collectLatest {
                delay(100)
                if (state.chat.messages.isNotEmpty()) {
                    messagesScrollState.animateScrollToItem(state.chat.messages.lastIndex)
                }
            }
        }
    }

    val requiredPermissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
    val permissionsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            if (permissions.all { it.value }) {
                navigateToCall()
            }
        }
    )
    var editingMessage by remember { mutableStateOf<Message?>(null) }
    var editingMessagePosition by remember { mutableStateOf(0) }

    Column {
        ChatTopAppBar(
            loading = state.loading,
            chat = state.chat,
            navigateUp = navigateUp,
            actions = {
                IconButton(
                    onClick = {
                        permissionsLauncher.launch(requiredPermissions)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Call,
                        contentDescription = null
                    )
                }
            }
        )

        when {
            state.loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    CircularProgressIndicator(strokeCap = StrokeCap.Round)
                }
            }

            state.chat.messages.isNotEmpty() -> {
                MessagesList(
                    messages = state.chat.messages,
                    scrollState = messagesScrollState,
                    openFullImage = { fullImagePath = it },
                    deleteMessage = { message ->
                        handleAction(ChatAction.DeleteMessage(message))
                    },
                    editMessage = { message, position ->
                        editingMessage = message
                        editingMessagePosition = position
                        handleAction(ChatAction.ChangeMessage(message.text))
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            else -> {
                NoMessagesCard(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )
            }
        }

        SendSection(
            message = state.enteredMessage,
            onMessageChange = { newMessage ->
                handleAction(ChatAction.ChangeMessage(newMessage))
            },
            onSendMessage = {
                handleAction(ChatAction.SendMessage())
//                scrollMessagesToBottom()
            },
            onAttachFiles = { files ->
                handleAction(ChatAction.AttachFiles(files))
//                scrollMessagesToBottom()
            },
            editingMessage = editingMessage,
            onEditingFinished = {
                handleAction(
                    ChatAction.EditMessage(
                        state.enteredMessage,
                        editingMessagePosition
                    )
                )
                editingMessage = null
                handleAction(ChatAction.ChangeMessage(""))
            },
            onEditingCanceled = {
                handleAction(ChatAction.ChangeMessage(""))
                editingMessage = null
            }
        )
    }

    AnimatedVisibility(
        visible = fullImagePath.isNotEmpty(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        FullScreenImage(
            fullImagePath = fullImagePath,
            contactName = state.chat.contactName,
            onClose = { fullImagePath = "" }
        )
    }
}

@ScreenPreview
@Composable
private fun ChatScreenPreview() {
    ChatterThemeWithSurface {
        ChatScreen(
            state = ChatState(),
            effect = flow {},
            handleAction = {},
            navigateToCall = {},
            navigateUp = {}
        )
    }
}