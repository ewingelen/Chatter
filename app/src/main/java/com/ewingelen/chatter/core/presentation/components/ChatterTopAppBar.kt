package com.ewingelen.chatter.core.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.ComponentPreview
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.ElevationMedium


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatterTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = title,
        navigationIcon = navigationIcon,
        actions = actions,
        modifier = modifier.shadow(
            elevation = ElevationMedium,
            ambientColor = MaterialTheme.colorScheme.primary,
            spotColor = MaterialTheme.colorScheme.primary
        ),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

@ComponentPreview
@Composable
private fun ChatterTopAppBarPreview() {
    ChatterTheme {
        ChatterTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = { IconButtonBack(onClick = {}) },
            actions = {}
        )
    }
}