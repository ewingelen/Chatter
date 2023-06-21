package com.ewingelen.chatter.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.ComponentPreview
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme

@Composable
fun ErrorText(
    text: String,
    visible: Boolean,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = modifier
    ) {
        Text(
            text = text,
            textAlign = textAlign,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@ComponentPreview
@Composable
private fun ErrorTextPreview() {
    ChatterTheme {
        ErrorText(
            text = stringResource(id = R.string.error_too_many_requests),
            visible = true
        )
    }
}