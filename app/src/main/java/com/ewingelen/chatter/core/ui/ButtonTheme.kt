package com.ewingelen.chatter.core.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import com.ewingelen.chatter.core.ui.theme.ButtonShapes
import com.ewingelen.chatter.core.ui.theme.Typography

//TODO: investigate about this implementation (learn theming. cross out on the roadmap)
@Composable
fun ButtonTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = ButtonShapes,
        content = content
    )
}
