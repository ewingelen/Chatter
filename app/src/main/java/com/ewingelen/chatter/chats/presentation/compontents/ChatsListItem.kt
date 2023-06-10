package com.ewingelen.chatter.chats.presentation.compontents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.R
import com.ewingelen.chatter.chats.presentation.ChatUi
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.ContactPhotoSize
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall150

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsListItem(
    chat: ChatUi,
    navigateToChat: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = navigateToChat,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(SpacingNormal100),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = stringResource(id = R.string.accessibility_contact_photo),
                modifier = Modifier
                    .size(ContactPhotoSize)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.small
                    )
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

@Preview
@Composable
private fun ChatsListItemPreview() {
    ChatterTheme {
        ChatsListItem(
            chat = ChatUi(
                userId = "r134314fqrqe",
                userAvatar = 0,
                userName = "Rebeca Donelli",
                lastMessage = "Pls take a look at the image I sent",
                time = "16:04",
                unreadMessagesCount = 2
            ),
            navigateToChat = {}
        )
    }
}