package com.ewingelen.chatter.chats.presentation.compontents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.chats.presentation.ChatUi
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall100

@Composable
fun ChatsList(
    chats: List<ChatUi>,
    navigateToChat: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(SpacingSmall100),
        contentPadding = PaddingValues(SpacingNormal100),
        modifier = modifier
    ) {
        items(items = chats) { chat ->
            ChatsListItem(
                chat = chat,
                navigateToChat = navigateToChat
            )
        }
    }
}

@Preview
@Composable
private fun ChatsListPreview() {
    ChatterTheme {
        ChatsList(
            chats = listOf(
                ChatUi(
                    userId = "r134314fqrqe",
                    userAvatar = 0,
                    userName = "Rebeca Donelli",
                    lastMessage = "Pls take a look at the image I sent",
                    time = "16:04",
                    unreadMessagesCount = 2
                ),
                ChatUi(
                    userId = "r134314fqrqe",
                    userAvatar = 0,
                    userName = "Rebeca Donelli",
                    lastMessage = "Pls take a look at the image I sent",
                    time = "16:04",
                    unreadMessagesCount = 2
                ),
                ChatUi(
                    userId = "r134314fqrqe",
                    userAvatar = 0,
                    userName = "Rebeca Donelli",
                    lastMessage = "Pls take a look at the image I sent",
                    time = "16:04",
                    unreadMessagesCount = 2
                )
            ),
            navigateToChat = {}
        )
    }
}