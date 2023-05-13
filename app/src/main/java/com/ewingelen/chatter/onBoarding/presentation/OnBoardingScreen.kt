package com.ewingelen.chatter.onBoarding.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithBackground

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@Composable
fun OnBoardingScreen(navigateToPhoneNumber: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = SpacingNormal100, vertical = SpacingLarge100),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = null,
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
            onClick = navigateToPhoneNumber,
            modifier = Modifier
                .fillMaxWidth()
                .height(ButtonHeightLarge)
        ) {
            Text(text = stringResource(id = R.string.button_start_messaging))
        }
    }
}

@ScreenPreview
@Composable
private fun OnBoardingScreenPreview() {
    ChatterThemeWithBackground {
        OnBoardingScreen(navigateToPhoneNumber = {})
    }
}