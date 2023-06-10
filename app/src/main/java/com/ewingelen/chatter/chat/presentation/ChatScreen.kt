package com.ewingelen.chatter.chat.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.ewingelen.chatter.R
import com.ewingelen.chatter.chat.presentation.contract.ChatAction
import com.ewingelen.chatter.chat.presentation.contract.ChatState
import com.ewingelen.chatter.core.presentation.theme.ContactPhotoSize
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall100
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall150
import com.ewingelen.chatter.core.presentation.components.ChatterTopAppBar
import com.ewingelen.chatter.core.presentation.components.IconButtonBack
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface


@Composable
fun ChatScreen(
    state: ChatState,
    handleAction: (ChatAction) -> Unit,
    navigateUp: () -> Unit
) {
    Column {
        ChatterTopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        imageVector = Icons.Default.Person,
                        contentDescription = stringResource(id = R.string.accessibility_contact_photo),
                        modifier = Modifier
                            .size(ContactPhotoSize)
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.small
                            ),
                    )

                    Spacer(modifier = Modifier.width(SpacingSmall100))

                    Text(
                        text = state.chatInfo.contactName,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                    //TODO: last online
                }
            },
            navigationIcon = { IconButtonBack(navigateUp = navigateUp) }
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(SpacingSmall100),
            contentPadding = PaddingValues(SpacingNormal100)
        ) {
            items(items = state.chatInfo.messages) { message ->
                //TODO: Move to mapper (maybe)
                var backgroundColor = MaterialTheme.colorScheme.surfaceVariant
                var alignment = Alignment.Start
                var shape = MaterialTheme.shapes.medium.copy(bottomStart = ZeroCornerSize)
                if (message.mine) {
                    backgroundColor = MaterialTheme.colorScheme.primary
                    alignment = Alignment.End
                    shape = MaterialTheme.shapes.medium.copy(bottomEnd = ZeroCornerSize)
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(alignment),
                    colors = CardDefaults.cardColors(backgroundColor),
                    shape = shape
                ) {
                    Text(
                        text = message.text,
                        modifier = Modifier.padding(SpacingSmall150)
                    )
                }
            }
        }

        ElevatedCard(
            shape = MaterialTheme.shapes.large.copy(
                bottomStart = ZeroCornerSize,
                bottomEnd = ZeroCornerSize
            ),
        ) {
            Row(
                modifier = Modifier.padding(SpacingNormal100),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = state.enteredMessage,
                    onValueChange = { handleAction(ChatAction.ChangeMessage(it)) },
                    placeholder = {
                        Text(text = stringResource(id = R.string.placeholder_write_message))
                    },
                    modifier = Modifier.weight(1f)
                )

                AnimatedVisibility(visible = state.enteredMessage.isNotEmpty()) {
                    Spacer(modifier = Modifier.width(SpacingNormal100))

                    FilledIconButton(onClick = { handleAction(ChatAction.SendMessage()) }) {
                        Icon(
                            imageVector = Icons.Rounded.Send,
                            contentDescription = stringResource(id = R.string.accessibility_send_message),
                        )
                    }
                }
            }
        }
    }
}

@ScreenPreview
@Composable
private fun ChatScreenPreview() {
    ChatterThemeWithSurface {
        ChatScreen(
            state = ChatState(),
            handleAction = {},
            navigateUp = {}
        )
    }
}