package com.ewingelen.chatter.chats.main.presentation.compontents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.presentation.components.ContactPhoto
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall100
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall150

@Composable
fun ChatsListItem(
    chat: Chat,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier.padding(SpacingSmall150),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ContactPhoto(
                photo = chat.contactPhoto,
                name = chat.contactName,
                surname = chat.contactSurname
            )

            Spacer(modifier = Modifier.width(SpacingNormal100))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${chat.contactName} ${chat.contactSurname}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(SpacingSmall100))

                Text(
                    text = if (chat.messages.isNotEmpty()) {
                        val lastMessage = chat.messages.last()
                        lastMessage.text.ifEmpty {
                            lastMessage.file!!.name
                        }
                    } else {
                        stringResource(id = R.string.label_no_messages_yet)
                    },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(SpacingNormal100))

//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Text(text = chat.time, maxLines = 1)
//
//                Spacer(modifier = Modifier.height(SpacingNormal100))
//
//                Badge(containerColor = MaterialTheme.colorScheme.primary) {
//                    Text(text = chat.unreadMessagesCount.toString())
//                }
//            }
        }
    }
}

@Preview
@Composable
private fun ChatsListItemPreview() {
    ChatterTheme {
    }
}