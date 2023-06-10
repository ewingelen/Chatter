package com.ewingelen.chatter.chats.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.ewingelen.chatter.R
import com.ewingelen.chatter.chats.presentation.compontents.ChatsList
import com.ewingelen.chatter.chats.presentation.compontents.EmptyChatsSection
import com.ewingelen.chatter.chats.presentation.contract.ChatsState
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.components.ChatterTopAppBar
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100

@Composable
fun ChatsScreen(
    state: ChatsState,
    navigateToCreateChat: () -> Unit,
    navigateToChat: () -> Unit
) {
    Column {
        ChatterTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            if (state.chats.isNotEmpty()) {
                ChatsList(
                    chats = state.chats,
                    navigateToChat = navigateToChat
                )
            } else {
                EmptyChatsSection(
                    modifier = Modifier
                        .padding(SpacingNormal100)
                        .align(Alignment.Center)
                )
            }

            FloatingActionButton(
                onClick = navigateToCreateChat,
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
}

@ScreenPreview
@Composable
private fun ChatsScreenPreview(
    @PreviewParameter(ChatsPreviewParameterProvider::class) state: ChatsState
) {
    ChatterThemeWithSurface {
        ChatsScreen(
            state = state,
            navigateToCreateChat = {},
            navigateToChat = {}
        )
    }
}