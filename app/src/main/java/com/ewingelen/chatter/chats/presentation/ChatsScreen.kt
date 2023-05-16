package com.ewingelen.chatter.chats.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.ChatUserPhotoImageSize
import com.ewingelen.chatter.core.presentation.ChatterTopAppBar
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.SpacingNormal100
import com.ewingelen.chatter.core.presentation.SpacingSmall100
import com.ewingelen.chatter.core.presentation.SpacingSmall150
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@OptIn(ExperimentalMaterial3Api::class)
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
            },
        )

        Box(modifier = Modifier.fillMaxSize()) {
            if (state.chats.isNotEmpty()) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(SpacingSmall100),
                    contentPadding = PaddingValues(SpacingNormal100)
                ) {
                    items(items = state.chats) { chat ->
                        Card(onClick = navigateToChat) {
                            Row(
                                modifier = Modifier.padding(SpacingNormal100),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.img_logo),
                                    contentDescription = stringResource(id = R.string.accessibility_user_photo),
                                    modifier = Modifier.size(ChatUserPhotoImageSize)
                                )

                                Spacer(modifier = Modifier.width(SpacingNormal100))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = chat.userName,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        fontWeight = FontWeight.Medium
                                    )

                                    Spacer(modifier = Modifier.height(SpacingSmall150))

                                    Text(
                                        text = chat.lastMessage,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }

                                Spacer(modifier = Modifier.width(SpacingNormal100))

                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = chat.time, maxLines = 1)

                                    Spacer(modifier = Modifier.height(SpacingNormal100))

                                    Badge(containerColor = MaterialTheme.colorScheme.primary) {
                                        Text(text = chat.unreadMessagesCount.toString())
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                Text(
                    text = stringResource(id = R.string.label_empty_chats),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
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
    ChatterThemeWithSurface(modifier = Modifier.fillMaxSize()) {
        ChatsScreen(
            state = state,
            navigateToCreateChat = {},
            navigateToChat = {}
        )
    }
}