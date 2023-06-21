package com.ewingelen.settings.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100

@Composable
fun SettingsScreen(
    englishSelected: Boolean,
    selectLanguage: (english: Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(SpacingNormal100)
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.label_language),
            style = MaterialTheme.typography.labelLarge
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = englishSelected,
                onClick = { selectLanguage(true) }
            )

            Spacer(modifier = Modifier.width(SpacingNormal100))

            Text(text = stringResource(id = R.string.label_english))
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = !englishSelected,
                onClick = { selectLanguage(false) }
            )

            Spacer(modifier = Modifier.width(SpacingNormal100))

            Text(text = stringResource(id = R.string.label_ukrainian))
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    ChatterTheme {
        SettingsScreen(
            englishSelected = true,
            selectLanguage = {}
        )
    }
}