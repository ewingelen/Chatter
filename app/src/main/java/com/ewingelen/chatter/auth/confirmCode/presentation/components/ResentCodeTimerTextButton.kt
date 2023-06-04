package com.ewingelen.chatter.auth.confirmCode.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.Gray900

@Composable
fun ResentCodeTimerTextButton(
    onClick: () -> Unit,
    enabled: Boolean,
    timeToResentLabel: String,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = Gray900)) {
                    append(text = stringResource(id = R.string.label_did_not_get_code))
                }
                pushStyle(ParagraphStyle())
                pushStyle(SpanStyle(color = MaterialTheme.colorScheme.onSurface))
                append(text = timeToResentLabel)
            },
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun ResentCodeTimerTextPreview() {
    ChatterTheme {
        ResentCodeTimerTextButton(
            onClick = {},
            enabled = false,
            timeToResentLabel = stringResource(id = R.string.label_format_try_again, 30)
        )
    }
}