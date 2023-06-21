package com.ewingelen.chatter.chats.main.presentation.compontents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100

@Composable
fun AppSectionTabs(
    selectedTab: Int,
    onTabSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = SpacingNormal100)
    ) {
        AppSectionTab(
            label = stringResource(id = R.string.tab_title_chats),
            selected = selectedTab == 0,
            onSelect = { onTabSelect(0) },
        )

        Spacer(modifier = Modifier.width(SpacingNormal100))

        AppSectionTab(
            label = stringResource(id = R.string.tab_title_me),
            selected = selectedTab == 1,
            onSelect = { onTabSelect(1) },
        )

        Spacer(modifier = Modifier.width(SpacingNormal100))

        AppSectionTab(
            label = stringResource(id = R.string.tab_title_settings),
            selected = selectedTab == 2,
            onSelect = { onTabSelect(2) },
        )
    }
}

@Preview
@Composable
private fun AppSectionTabsPreview() {
    ChatterTheme {
        AppSectionTabs(
            selectedTab = 0,
            onTabSelect = {}
        )
    }
}