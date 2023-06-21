package com.ewingelen.chatter.chat.presentation.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.ComponentPreview
import com.ewingelen.chatter.core.presentation.components.IconButtonBack
import com.ewingelen.chatter.core.presentation.theme.Black900
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.White900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenImage(
    contactName: String,
    fullImagePath: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onClose)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Black900)
    ) {
        AsyncImage(
            model = fullImagePath,
            contentDescription = stringResource(id = R.string.accessibility_photo),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        )

        TopAppBar(
            title = {
                Text(text = contactName, style = MaterialTheme.typography.labelLarge)
            },
            navigationIcon = {
                IconButtonBack(onClick = onClose)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Black900.copy(alpha = 0.5f),
                titleContentColor = White900,
                navigationIconContentColor = White900
            )
        )
    }
}

@ComponentPreview
@Composable
private fun FullScreenImagePreview() {
    ChatterTheme {
        FullScreenImage(
            contactName = "Artem",
            fullImagePath = "",
            onClose = {}
        )
    }
}