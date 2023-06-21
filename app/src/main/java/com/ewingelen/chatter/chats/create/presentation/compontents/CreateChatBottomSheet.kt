package com.ewingelen.chatter.chats.create.presentation.compontents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.R
import com.ewingelen.chatter.chats.create.presentation.contract.CreateChatAction
import com.ewingelen.chatter.chats.create.presentation.contract.CreateChatEffect
import com.ewingelen.chatter.chats.create.presentation.contract.CreateChatState
import com.ewingelen.chatter.chats.create.presentation.contract.HandleCreateChatEffect
import com.ewingelen.chatter.core.presentation.components.ChatterOutlinedTextField
import com.ewingelen.chatter.core.presentation.components.ErrorText
import com.ewingelen.chatter.core.presentation.theme.ButtonHeightLarge
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal150
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall100
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateChatBottomSheet(
    state: CreateChatState.Base,
    effect: Flow<CreateChatEffect>,
    handleAction: (CreateChatAction) -> Unit,
    visible: Boolean,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val handleEffect = object : HandleCreateChatEffect {
        override fun chatCreated() {
            onDismissRequest()
        }
    }
    LaunchedEffect(key1 = Unit) {
        effect.collectLatest { effect ->
            effect.handle(handleEffect)
        }
    }

    if (visible) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = SpacingNormal100,
                        end = SpacingNormal100,
                        bottom = SpacingNormal100
                    )
                    .imePadding()
            ) {
                Text(
                    text = stringResource(id = R.string.title_create_chat),
                    style = MaterialTheme.typography.headlineMedium,
                )

                Spacer(modifier = Modifier.height(SpacingNormal150))

                ChatterOutlinedTextField(
                    value = state.phoneNumber,
                    onValueChange = { newValue ->
                        handleAction(CreateChatAction.ChangePhoneNumber(newValue))
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.Phone, contentDescription = null)
                    },
                    labelResourceId = R.string.label_phone_number,
                    placeholderResourceId = R.string.placeholder_enter_contact_phone_number,
                    isError = state.phoneNumberErrorVisible,
                    prefix = {
                        Text(text = stringResource(id = R.string.symbol_plus))
                    },
                    supportingText = {
                        AnimatedVisibility(visible = state.phoneNumberErrorVisible) {
                            Text(text = state.phoneNumberError)
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(SpacingSmall100))

                ChatterOutlinedTextField(
                    value = state.name,
                    onValueChange = { newValue ->
                        handleAction(CreateChatAction.ChangeName(newValue))
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.Person, contentDescription = null)
                    },
                    labelResourceId = R.string.label_name,
                    placeholderResourceId = R.string.placeholder_enter_contact_name,
                    singleLine = true,
                    isError = state.emptyNameErrorVisible,
                    supportingText = {
                        AnimatedVisibility(visible = state.emptyNameErrorVisible) {
                            Text(text = stringResource(id = R.string.error_empty_contact_name))
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(SpacingNormal100))

                ChatterOutlinedTextField(
                    value = state.surname,
                    onValueChange = { newValue ->
                        handleAction(CreateChatAction.ChangeSurname(newValue))
                    },
                    labelResourceId = R.string.label_surname,
                    placeholderResourceId = R.string.placeholder_surname,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                ErrorText(
                    text = state.createChatError,
                    visible = state.createChatErrorVisible,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(SpacingNormal100))

                Button(
                    onClick = {
                        handleAction(CreateChatAction.CreateChat())
                    },
                    enabled = !state.emptyNameErrorVisible && !state.phoneNumberErrorVisible,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ButtonHeightLarge)
                ) {
                    Text(text = stringResource(id = R.string.label_create_chat))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CreateChatBottomSheetPreview() {
    ChatterTheme {
        CreateChatBottomSheet(
            state = CreateChatState.Base(),
            effect = flow {},
            handleAction = {},
            onDismissRequest = {},
            visible = true,
            sheetState = rememberModalBottomSheetState()
        )
    }
}