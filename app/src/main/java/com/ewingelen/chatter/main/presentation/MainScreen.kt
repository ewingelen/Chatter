package com.ewingelen.chatter.main.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.theme.ScreenTheme

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@Composable
fun MainScreen() {
    Text(text = "MAIN SCREEN")
}

@ScreenPreview
@Composable
private fun MainScreenPreview() {
    ScreenTheme {
        MainScreen()
    }
}