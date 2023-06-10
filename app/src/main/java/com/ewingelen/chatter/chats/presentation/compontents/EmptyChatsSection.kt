package com.ewingelen.chatter.chats.presentation.compontents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall100

@Composable
fun EmptyChatsSection(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_empty_section),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(SpacingNormal100))

        Text(
            text = stringResource(id = R.string.title_empty_chats),
            style = MaterialTheme.typography.headlineMedium,
        )

        Spacer(modifier = Modifier.height(SpacingSmall100))

        Text(
            text = stringResource(id = R.string.subtitle_empty_chats),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun EmptyChatsSectionPreview() {
    ChatterTheme {
        EmptyChatsSection()
    }
}