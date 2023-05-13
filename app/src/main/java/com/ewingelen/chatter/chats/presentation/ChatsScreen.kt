package com.ewingelen.chatter.chats.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithBackground

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@Composable
fun ChatsScreen() {
    Text(text = "CHATS SCREEN")
}

@ScreenPreview
@Composable
private fun ChatsScreenPreview() {
    ChatterThemeWithBackground {
        ChatsScreen()
    }
}