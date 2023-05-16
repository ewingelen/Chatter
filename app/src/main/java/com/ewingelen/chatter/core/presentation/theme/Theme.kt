package com.ewingelen.chatter.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
private val DarkColorPalette = darkColorScheme(
    primary = Purple200,
    secondary = Teal200,
    background = Black800,
    onSurfaceVariant = Gray900
)

private val LightColorPalette = lightColorScheme(
    primary = Blue200,
    onPrimary = Black900,
    secondary = Teal200,
    background = White900,
    onSurfaceVariant = Black900,
    surfaceVariant = Gray200
)

@Composable
fun ChatterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}