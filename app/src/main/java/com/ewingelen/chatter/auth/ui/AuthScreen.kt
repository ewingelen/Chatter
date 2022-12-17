package com.ewingelen.chatter.auth.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ewingelen.chatter.core.ui.theme.ChatterTheme

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel()
) {
    Column {
        Text("")
        Button(
            onClick = { viewModel.auth() }
        ) {
            Text("AUTH")
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES, device = Devices.TABLET)
@Composable
private fun PreviewAuthScreen() {
    ChatterTheme {
        AuthScreen()
    }
}