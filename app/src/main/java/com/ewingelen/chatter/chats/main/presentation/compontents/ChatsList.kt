package com.ewingelen.chatter.chats.main.presentation.compontents

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.chat.presentation.components.MessageDropdownMenu
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall100

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatsList(
    chats: List<Chat>,
    navigateToChat: (chatId: String) -> Unit,
    onDeleteChat: (Chat) -> Unit,
    modifier: Modifier = Modifier
) {
    var chatWithDropdownExpanded by rememberSaveable { mutableStateOf("") }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(SpacingSmall100),
        contentPadding = PaddingValues(SpacingNormal100),
        modifier = modifier
    ) {
        items(items = chats) { chat ->
            Box {
                ChatsListItem(
                    chat = chat,
                    modifier = Modifier.combinedClickable(
                        onClick = {
                            navigateToChat(chat.id)
                        },
                        onLongClick = {
                            chatWithDropdownExpanded = chat.id
                        }
                    )
                )
                if (chatWithDropdownExpanded == chat.id) {
                    MessageDropdownMenu(
                        onDelete = { onDeleteChat(chat) },
                        onEdit = { },
                        expanded = true,
                        onDismissRequest = { chatWithDropdownExpanded = "empty" },
                        deleteAvailable = true,
                        editAvailable = false
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ChatsListPreview() {
    ChatterTheme {
    }
}