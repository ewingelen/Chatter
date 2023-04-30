package com.ewingelen.chatter.auth.verifyPhone.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.ScreenTheme

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@Composable
fun LoadingFullscreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()

            Spacer(modifier = Modifier.height(SpacingNormal100))

            Text(text = stringResource(id = R.string.label_please_wait))
        }
    }
}

@ScreenPreview
@Composable
fun LoadingScreenPreview() {
    ScreenTheme {
        LoadingFullscreen()
    }
}