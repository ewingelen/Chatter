package com.ewingelen.chatter.createChat.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.ButtonHeightLarge
import com.ewingelen.chatter.core.presentation.ChatterTopAppBar
import com.ewingelen.chatter.core.presentation.IconButtonBack
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
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

        Column(modifier = Modifier.padding(SpacingNormal100)) {
            BasicTextField(
                value = state.name,
                onValueChange = { newName ->
                    handleAction(CreateChatAction.ChangeName(newName))
                },
                modifier = Modifier.fillMaxWidth()
            )

            BasicTextField(
                value = state.phoneNumber,
                onValueChange = { newNumber ->
                    handleAction(CreateChatAction.ChangePhoneNumber(newNumber))
                },
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