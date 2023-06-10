package com.ewingelen.chatter.auth.createProfile.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.components.ErrorText
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.SpacingLarge100
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100

@Composable
fun CreateProfileLoadingDialog(
    noInternetWarningVisible: Boolean,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Card(modifier = modifier.height(IntrinsicSize.Min)) {
            AnimatedVisibility(
                visible = noInternetWarningVisible,
                modifier = Modifier
                    .weight(1f)
                    .padding(SpacingLarge100)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.img_no_internet_connection),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.height(SpacingNormal100))

                    ErrorText(
                        text = stringResource(id = R.string.error_waiting_internet_connection),
                        textAlign = TextAlign.Center,
                        visible = true
                    )
                }
            }

            AnimatedVisibility(
                visible = !noInternetWarningVisible,
                modifier = Modifier
                    .weight(1f)
                    .padding(SpacingLarge100)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator(strokeCap = StrokeCap.Round)

                    Spacer(modifier = Modifier.height(SpacingNormal100))

                    AnimatedVisibility(visible = !noInternetWarningVisible) {
                        Text(text = stringResource(id = R.string.label_loading_create_profile))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CreateProfileLoadingDialogPreview() {
    ChatterTheme {
        CreateProfileLoadingDialog(noInternetWarningVisible = false)
    }
}