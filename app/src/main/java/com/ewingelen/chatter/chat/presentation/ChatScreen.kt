package com.ewingelen.chatter.chat.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ewingelen.chatter.core.presentation.ChatterTopAppBar
import com.ewingelen.chatter.core.presentation.IconButtonBack
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
@Composable
fun ChatScreen(navigateUp: () -> Unit) {
    Column {
        ChatterTopAppBar(
            title = {},
            navigationIcon = { IconButtonBack(navigateUp = navigateUp) }
        )

        Text(text = "ChatScreen")
    }
}

@ScreenPreview
@Composable
private fun ChatScreenPreview() {
    ChatterThemeWithSurface {
        ChatScreen(navigateUp = {})
    }
}