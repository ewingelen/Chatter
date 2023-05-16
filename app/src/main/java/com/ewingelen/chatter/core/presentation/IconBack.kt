package com.ewingelen.chatter.core.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
@Composable
fun IconButtonBack(navigateUp: () -> Unit) {
    IconButton(onClick = navigateUp) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = stringResource(id = R.string.accessibility_previous_page)
        )
    }
}

@ComponentPreview
@Composable
private fun IconBackPreview() {
    ChatterTheme {
        IconButtonBack(navigateUp = {})
    }
}