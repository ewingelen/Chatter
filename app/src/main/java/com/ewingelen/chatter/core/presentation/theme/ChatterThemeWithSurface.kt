package com.ewingelen.chatter.core.presentation.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ewingelen.chatter.core.presentation.ComponentPreview


@Composable
fun ChatterThemeWithSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    ChatterTheme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@ComponentPreview
@Composable
private fun ChatterThemeWithBackgroundPreview() {
    ChatterThemeWithSurface {}
}