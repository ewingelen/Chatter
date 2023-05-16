package com.ewingelen.chatter.core.presentation

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
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatterTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = title,
        navigationIcon = navigationIcon,
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
            navigationIcon = { IconButtonBack(navigateUp = {}) }
        )
    }
}