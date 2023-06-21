package com.ewingelen.chatter.chat.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.presentation.ComponentPreview
import com.ewingelen.chatter.core.presentation.components.ChatterTopAppBar
import com.ewingelen.chatter.core.presentation.components.ContactPhoto
import com.ewingelen.chatter.core.presentation.components.IconButtonBack
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall100

@Composable
fun ChatTopAppBar(
    loading: Boolean,
    chat: Chat,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {}
) {
    ChatterTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (!loading) {
                    ContactPhoto(
                        photo = chat.contactPhoto,
                        name = chat.contactName,
                        surname = chat.contactSurname
                    )

                    Spacer(modifier = Modifier.width(SpacingSmall100))
                }

                Text(
                    text = if (loading) {
                        stringResource(id = R.string.label_please_wait)
                    } else {
                        "${chat.contactName} ${chat.contactSurname}"
                    },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelLarge
                )
                //TODO: last online
            }
        },
        navigationIcon = {
            IconButtonBack(onClick = navigateUp)
        },
        actions = actions,
        modifier = modifier
    )
}

@ComponentPreview
@Composable
private fun ChatTopAppBarPreview() {
    ChatterTheme {
        ChatTopAppBar(
            loading = false,
            chat = Chat(),
            navigateUp = {}
        )
    }
}