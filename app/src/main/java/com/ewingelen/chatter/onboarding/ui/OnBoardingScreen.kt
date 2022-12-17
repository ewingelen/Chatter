package com.ewingelen.chatter.onboarding.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.ui.ButtonTheme
import com.ewingelen.chatter.core.ui.LocalDimens
import com.ewingelen.chatter.core.ui.ScreenTheme
import com.ewingelen.chatter.core.ui.theme.ChatterTheme

@Composable
fun OnBoardingScreen(
    launchSignUpScreen: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = LocalDimens.current.grid_0_25),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h4
            )

            Spacer(modifier = Modifier.height(64.dp))

            Image(
                painter = painterResource(id = R.drawable.img_on_boarding),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(64.dp))

            Text(
                stringResource(id = R.string.label_on_boarding_body),
                textAlign = TextAlign.Center
            )
        }

        ButtonTheme {
            Button(
                onClick = { launchSignUpScreen() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp),
            ) {
                Text(stringResource(id = R.string.label_start_messaging), color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES, device = Devices.TABLET)
@Composable
private fun OnBoardingScreenPreview() {
    ChatterTheme {
        ScreenTheme {
            OnBoardingScreen {

            }
        }
    }
}