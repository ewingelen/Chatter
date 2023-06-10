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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.components.ChatterOutlinedTextField
import com.ewingelen.chatter.core.presentation.components.ChatterTopAppBar
import com.ewingelen.chatter.core.presentation.components.ErrorText
import com.ewingelen.chatter.core.presentation.components.IconButtonBack
import com.ewingelen.chatter.core.presentation.theme.ButtonHeightLarge
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface
import com.ewingelen.chatter.core.presentation.theme.SpacingLarge100
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal150
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall100
import com.ewingelen.chatter.createChat.presentation.contract.CreateChatAction
import com.ewingelen.chatter.createChat.presentation.contract.CreateChatEffect
import com.ewingelen.chatter.createChat.presentation.contract.CreateChatState
import com.ewingelen.chatter.createChat.presentation.contract.HandleCreateChatEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow


//TODO: select from contacts
@Composable
fun CreateChatScreen(
    state: CreateChatState,
    handleAction: (CreateChatAction) -> Unit,
    effect: Flow<CreateChatEffect>,
    showSnackbar: (String) -> Unit,
    navigateUp: () -> Unit,
) {
    val chatCreatedSnackbarMessage = stringResource(id = R.string.snackbar_chat_created)

    LaunchedEffect(key1 = Unit) {
        val handleEffect = object : HandleCreateChatEffect {
            override fun chatCreated() {
                showSnackbar(chatCreatedSnackbarMessage)
                navigateUp()
            }
        }
        effect.collectLatest { effect ->
            effect.handle(handleEffect)
        }
    }

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
            modifier = Modifier.padding(horizontal = SpacingNormal100, vertical = SpacingLarge100)
        ) {
            Text(
                text = stringResource(id = R.string.subtitle_add_chat),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(SpacingNormal150))

            ChatterOutlinedTextField(
                value = state.name,
                onValueChange = { handleAction(CreateChatAction.ChangeName(it)) },
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Person, contentDescription = null)
                },
                labelResourceId = R.string.label_name,
                placeholderResourceId = R.string.placeholder_enter_contact_name,
                singleLine = true,
                isError = state.errorEmptyNameShowing,
                supportingText = {
                    Text(text = state.errorEmptyName)
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(SpacingSmall100))

            ChatterOutlinedTextField(
                value = state.phoneNumber,
                onValueChange = {
                    handleAction(CreateChatAction.ChangePhoneNumber(it))
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Phone, contentDescription = null)
                },
                labelResourceId = R.string.label_phone_number,
                placeholderResourceId = R.string.placeholder_enter_contact_phone_number,
                isError = state.errorEmptyPhoneNumberShowing,
                prefix = {
                    Text(text = stringResource(id = R.string.symbol_plus))
                },
                supportingText = {
                    Text(text = state.errorEmptyPhoneNumber)
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(SpacingNormal100))

            ErrorText(text = state.error, visible = state.errorVisible)

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    handleAction(CreateChatAction.CreateChat())
                },
                enabled = !state.errorEmptyNameShowing && !state.errorEmptyPhoneNumberShowing,
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
            effect = flow {},
            handleAction = {},
            showSnackbar = {},
            navigateUp = {}
        )
    }
}