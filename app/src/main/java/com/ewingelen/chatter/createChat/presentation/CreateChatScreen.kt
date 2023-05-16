package com.ewingelen.chatter.createChat.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.ButtonHeightLarge
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.SpacingLarge100
import com.ewingelen.chatter.core.presentation.SpacingNormal100
import com.ewingelen.chatter.core.presentation.components.ChatterOutlinedTextField
import com.ewingelen.chatter.core.presentation.components.ChatterTopAppBar
import com.ewingelen.chatter.core.presentation.components.IconButtonBack
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
//TODO: select from contacts
@Composable
fun CreateChatScreen(
    state: CreateChatState,
    handleAction: (CreateChatAction) -> Unit,
    navigateUp: () -> Unit
) {
    Column {
        ChatterTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.top_bar_title_new_chat),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            navigationIcon = { IconButtonBack(navigateUp = navigateUp) }
        )

        Column(
            modifier = Modifier.padding(
                start = SpacingNormal100,
                top = SpacingNormal100,
                end = SpacingNormal100,
                bottom = SpacingLarge100
            )
        ) {
            Text(
                text = stringResource(id = R.string.subtitle_add_chat),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(SpacingNormal100))

            ChatterOutlinedTextField(
                value = state.name,
                onValueChange = { handleAction(CreateChatAction.ChangeName(it)) },
                leadingIcon = Icons.Rounded.Person,
                labelResourceId = R.string.label_name,
                placeholderResourceId = R.string.placeholder_enter_contact_name,
                modifier = Modifier.fillMaxWidth()
            )

            ChatterOutlinedTextField(
                value = state.name,
                onValueChange = { handleAction(CreateChatAction.ChangePhoneNumber(it)) },
                leadingIcon = Icons.Rounded.Phone,
                labelResourceId = R.string.label_phone_number,
                placeholderResourceId = R.string.placeholder_enter_contact_phone_number,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { handleAction(CreateChatAction.CreateChat()) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ButtonHeightLarge)
            ) {
                Text(text = stringResource(id = R.string.label_create_chat))
            }
        }
    }
}

@ScreenPreview
@Composable
private fun CreateChatScreenPreview() {
    ChatterThemeWithSurface {
        CreateChatScreen(
            state = CreateChatState(),
            handleAction = {},
            navigateUp = {}
        )
    }
}