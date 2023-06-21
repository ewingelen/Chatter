package com.ewingelen.chatter.chat.presentation.components

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.domain.model.Message
import com.ewingelen.chatter.core.presentation.ComponentPreview
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.SpacingExtraSmall100
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall100

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MessagesList(
    messages: List<Message>,
    openFullImage: (String) -> Unit,
    scrollState: LazyListState,
    deleteMessage: (Message) -> Unit,
    editMessage: (Message, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var messageWithDropdownOpened by rememberSaveable { mutableStateOf("empty") }
    val imageFormats = listOf("jpg", "png", "gif", "jpeg")
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(SpacingSmall100),
        contentPadding = PaddingValues(SpacingNormal100),
        state = scrollState
    ) {
        itemsIndexed(items = messages) { index, message ->
            if (message.text.isNotEmpty()) {
                MessageCard(
                    mine = message.mine,
                    dropdownMenu = {
                        if (messageWithDropdownOpened == message.id) {
                            MessageDropdownMenu(
                                onDelete = { deleteMessage(message) },
                                onEdit = {
                                    editMessage(message, index)
                                    messageWithDropdownOpened = "empty"
                                },
                                expanded = true,
                                editAvailable = message.mine,
                                deleteAvailable = message.mine,
                                onDismissRequest = { messageWithDropdownOpened = "empty" },
                            )
                        }
                    },
                    modifier = Modifier.clickable {
                        messageWithDropdownOpened = message.id
                    }
                ) {
                    Text(
                        text = message.text,
                        modifier = Modifier.padding(SpacingSmall100)
                    )
                }
            } else {
                message.file?.let { file ->
                    if (imageFormats.any { file.type.endsWith(it) }) {
                        Row(
                            horizontalArrangement = if (message.mine) {
                                Arrangement.End
                            } else {
                                Arrangement.Start
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box {
                                AsyncImage(
                                    model = file.downloadUrl,
                                    contentDescription = stringResource(id = R.string.accessibility_photo),
                                    placeholder = painterResource(id = R.drawable.ic_photo),
                                    error = painterResource(id = R.drawable.ic_photo),
                                    contentScale = ContentScale.Crop,
                                    filterQuality = FilterQuality.Low,
                                    modifier = Modifier
                                        .size(200.dp)
                                        .clip(shape = MaterialTheme.shapes.medium)
                                        .background(color = MaterialTheme.colorScheme.primary)
                                        .then(
                                            if(message.mine) {
                                                Modifier.combinedClickable(
                                                    onClick = {
                                                        openFullImage(file.downloadUrl)
                                                    },
                                                    onLongClick = {
                                                        messageWithDropdownOpened = message.id
                                                    }
                                                )
                                            } else {
                                                Modifier
                                            }
                                        )
                                )
                                MessageDropdownMenu(
                                    onDelete = {
                                        deleteMessage(message)
                                    },
                                    onEdit = {},
                                    expanded = messageWithDropdownOpened == message.id,
                                    onDismissRequest = { messageWithDropdownOpened = "empty" },
                                    deleteAvailable = message.mine,
                                    editAvailable = false
                                )
                            }
                        }
                    } else {
                        MessageCard(
                            mine = message.mine,
                            dropdownMenu = {
                                MessageDropdownMenu(
                                    onDelete = { deleteMessage(message) },
                                    onEdit = { },
                                    expanded = messageWithDropdownOpened == message.id,
                                    editAvailable = false,
                                    deleteAvailable = message.mine,
                                    onDismissRequest = { messageWithDropdownOpened = "empty" }
                                )
                            },
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.medium)
                                .then(
                                    if (message.mine) {
                                        Modifier.combinedClickable(
                                            onClick = {
                                                val intent = Intent(Intent.ACTION_VIEW).apply {
                                                    setDataAndType(
                                                        file.downloadUrl.toUri(),
                                                        file.type
                                                    )
                                                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                                }
                                                val newIntent =
                                                    Intent.createChooser(intent, "Open File");
                                                try {
                                                    context.startActivity(newIntent)
                                                } catch (_: ActivityNotFoundException) {
                                                }
                                            },
                                            onLongClick = {
                                                messageWithDropdownOpened = message.id
                                            }
                                        )
                                    } else {
                                        Modifier
                                    }
                                )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(SpacingSmall100)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_file),
                                    contentDescription = stringResource(id = R.string.accessibility_file),
                                    modifier = Modifier.size(40.dp)
                                )

                                Spacer(modifier = Modifier.width(SpacingExtraSmall100))

                                Column {
                                    if (file.name.isNotEmpty()) {
                                        Text(text = file.name, maxLines = 1)
                                    }

                                    Spacer(modifier = Modifier.height(SpacingSmall100))

                                    Text(
                                        text = "${file.size} MB ${file.type.split("/").last()}",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@ComponentPreview
@Composable
private fun MessagesListPreview() {
    ChatterTheme {
        MessagesList(
            messages = emptyList(),
            scrollState = rememberLazyListState(),
            deleteMessage = {},
            editMessage = { _, _ -> },
            openFullImage = {}
        )
    }
}