package com.ewingelen.chatter.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ewingelen.chatter.core.presentation.ComponentPreview

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@Composable
fun ChatterThemeWithBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    ChatterTheme {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@ComponentPreview
@Composable
private fun ChatterThemeWithBackgroundPreview() {
    ChatterThemeWithBackground {}
}