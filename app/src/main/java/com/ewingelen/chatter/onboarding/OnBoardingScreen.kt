package com.ewingelen.chatter.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.ButtonHeightLarge
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.SpacingLarge100
import com.ewingelen.chatter.core.presentation.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.ScreenTheme

@Composable
fun OnBoardingScreen(navigateToAuth: () -> Unit) {
    Column(
        modifier = Modifier.padding(horizontal = SpacingNormal100, vertical = SpacingLarge100),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(SpacingLarge100))

        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(SpacingLarge100))

        Text(
            text = stringResource(id = R.string.label_on_boarding_body),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = navigateToAuth,
            modifier = Modifier
                .fillMaxWidth()
                .height(ButtonHeightLarge)
        ) {
            Text(text = stringResource(id = R.string.btn_start_messaging))
        }
    }
}

@ScreenPreview
@Composable
private fun OnBoardingScreenPreview() {
    ScreenTheme {
        OnBoardingScreen(navigateToAuth = {})
    }
}