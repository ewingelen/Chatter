package com.ewingelen.chatter.chats.main.presentation.compontents

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.SpacingExtraSmall100
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSectionTab(
    label: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onSelect,
        colors = CardDefaults.cardColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent
        ),
        shape = CircleShape,
        modifier = modifier
    ) {
        Text(
            text = label,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(horizontal = SpacingNormal100, vertical = SpacingExtraSmall100)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
private fun AppSectionTabPreview() {
    ChatterTheme {
        AppSectionTab(
            label = stringResource(id = R.string.tab_title_chats),
            selected = true,
            onSelect = {}
        )
    }
}