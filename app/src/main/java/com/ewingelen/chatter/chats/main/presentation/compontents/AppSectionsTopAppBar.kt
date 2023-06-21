package com.ewingelen.chatter.chats.main.presentation.compontents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.ElevationMedium
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSectionsTopAppBar(
    title: String,
    selectedTab: Int,
    onTabSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    Box(modifier = modifier) {
        MediumTopAppBar(
            title = {
                if (scrollBehavior.state.collapsedFraction < 0.32) {
                    AppSectionTabs(
                        selectedTab = selectedTab,
                        onTabSelect = onTabSelect
                    )
                }
            },
            scrollBehavior = scrollBehavior,
            modifier = Modifier.shadow(
                elevation = ElevationMedium,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary
            )
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            modifier = Modifier.padding(SpacingNormal100)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun AppSectionsTopAppBarPreview() {
    ChatterTheme {
        AppSectionsTopAppBar(
            title = stringResource(id = R.string.app_name),
            selectedTab = 0,
            onTabSelect = {},
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
                rememberTopAppBarState()
            )
        )
    }
}