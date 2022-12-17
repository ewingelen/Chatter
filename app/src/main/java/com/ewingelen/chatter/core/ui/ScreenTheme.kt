package com.ewingelen.chatter.core.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//TODO: investigate about this implementation (learn theming. cross out on the roadmap)
@Composable
fun ScreenTheme(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize().padding(),
        color = MaterialTheme.colors.background
    ) {
        content()
    }
}