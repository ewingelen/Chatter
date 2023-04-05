package com.ewingelen.chatter.core.presentation.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ewingelen.chatter.core.presentation.ElementPreview

@Composable
fun ScreenTheme(content: @Composable () -> Unit) {
    ChatterTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}

@ElementPreview
@Composable
private fun ScreenThemePreview() {
    ScreenTheme {}
}