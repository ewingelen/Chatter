package com.ewingelen.chatter.chat.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.SpacingLarge100
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall150

@Composable
fun MessageCard(
    mine: Boolean,
    modifier: Modifier = Modifier,
    dropdownMenu: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    var backgroundColor = MaterialTheme.colorScheme.surfaceVariant
    var alignment = Alignment.Start
    var shape = MaterialTheme.shapes.medium.copy(bottomStart = ZeroCornerSize)
    var padding = PaddingValues(end = SpacingLarge100)
    if (mine) {
        backgroundColor = MaterialTheme.colorScheme.primary
        alignment = Alignment.End
        shape = MaterialTheme.shapes.medium.copy(bottomEnd = ZeroCornerSize)
        padding = PaddingValues(start = SpacingLarge100)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(alignment)
            .padding(padding)
            .clip(shape)
            .then(modifier)
    ) {
        Card(
            colors = CardDefaults.cardColors(backgroundColor),
            shape = shape
        ) {
            content()
        }

        dropdownMenu()
    }
}

@Preview
@Composable
private fun MessageCardPreview() {
    ChatterTheme {
        MessageCard(
            mine = true,
            dropdownMenu = {},
            content = {
                Text(
                    text = "Hello",
                    modifier = Modifier.padding(SpacingSmall150)
                )
            }
        )
    }
}