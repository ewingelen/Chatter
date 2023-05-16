package com.ewingelen.chatter.chats.presentation

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 14.05.2023.
 */
class ChatsPreviewParameterProvider : PreviewParameterProvider<ChatsState> {

    override val values = sequenceOf(
        ChatsState(
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
            )
        )
    )
}