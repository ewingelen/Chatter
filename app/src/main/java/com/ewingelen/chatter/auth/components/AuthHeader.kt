package com.ewingelen.chatter.auth.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.ScreenTheme

/**
 * Created by Artem Skorik email(artem.newage@outlook.com) on 05.04.2023.
 */
@Composable
fun AuthHeader(
    @StringRes titleResourceId: Int,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = titleResourceId),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(SpacingNormal100))

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun AuthHeaderPreview() {
    ScreenTheme {
        AuthHeader(
            titleResourceId = R.string.label_enter_phone_number,
            subtitle = stringResource(id = R.string.label_confirm_region)
        )
    }
}