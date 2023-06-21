package com.ewingelen.chatter.chat.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.ComponentPreview
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall100

@Composable
fun NoMessagesCard(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Card(shape = CircleShape) {
            Text(
                text = stringResource(id = R.string.label_no_messages_yet),
                modifier = Modifier.padding(SpacingSmall100)
            )
        }
    }
}

@ComponentPreview
@Composable
private fun NoMessagesCardPreview() {
    ChatterTheme {
        NoMessagesCard()
    }
}