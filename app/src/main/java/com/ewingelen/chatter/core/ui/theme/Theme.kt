package com.ewingelen.chatter.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.ewingelen.chatter.core.ui.LocalDimens
import com.ewingelen.chatter.core.ui.dimensions

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Black800
)

private val LightColorPalette = lightColors(
    primary = Blue200,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = White900
)

@Composable
fun ChatterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

//    val configuration = LocalConfiguration.current
//    val dimensions = if(configuration.screenWidthDp <= 360) dimensions else dimensionsLarge
//    val typography = if (configuration.screenWidthDp <= 360) smallTypography else sw360Typography
    CompositionLocalProvider(LocalDimens provides dimensions ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}