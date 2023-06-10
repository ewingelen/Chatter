package com.ewingelen.chatter.auth.createProfile.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ewingelen.chatter.R
import com.ewingelen.chatter.auth.createProfile.presentation.components.CreateProfileLoadingDialog
import com.ewingelen.chatter.auth.createProfile.presentation.components.CreateProfilePhoto
import com.ewingelen.chatter.auth.createProfile.presentation.components.CreateProfileTextField
import com.ewingelen.chatter.auth.createProfile.presentation.contract.CreateProfileAction
import com.ewingelen.chatter.auth.createProfile.presentation.contract.CreateProfileEffect
import com.ewingelen.chatter.auth.createProfile.presentation.contract.CreateProfileState
import com.ewingelen.chatter.auth.createProfile.presentation.contract.HandleCreateProfileEffect
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.components.ErrorText
import com.ewingelen.chatter.core.presentation.components.ScreenHeader
import com.ewingelen.chatter.core.presentation.shake
import com.ewingelen.chatter.core.presentation.theme.ButtonHeightLarge
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface
import com.ewingelen.chatter.core.presentation.theme.SpacingLarge100
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun CreateProfileScreen(
    state: CreateProfileState.Base,
    effect: Flow<CreateProfileEffect>,
    handleAction: (CreateProfileAction) -> Unit,
    navigateToChats: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        val handleEffect = object : HandleCreateProfileEffect {
            override fun profileCreated() {
                navigateToChats()
            }
        }
        effect.collect { effect ->
            effect.handle(handleEffect)
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = SpacingNormal100, vertical = SpacingLarge100),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader(
            titleResourceId = R.string.title_create_profile,
            subtitle = stringResource(id = R.string.subtitle_create_profile)
        )

        Spacer(modifier = Modifier.height(SpacingNormal100))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            CreateProfilePhoto(
                addPhoto = { uri ->
                    handleAction(CreateProfileAction.AddPhoto(uri))
                },
                photo = state.photo
            )

            Spacer(modifier = Modifier.width(SpacingNormal100))

            Column {
                CreateProfileTextField(
                    value = state.name,
                    onValueChange = { newValue ->
                        handleAction(CreateProfileAction.ChangeName(newValue))
                    },
                    placeholderText = stringResource(id = R.string.placeholder_name),
                    trailingIcon = {
                        AnimatedVisibility(visible = state.name.isBlank()) {
                            Text(
                                text = stringResource(id = R.string.symbol_asterisk),
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.shake(state.emptyNameErrorShowing)
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(SpacingNormal100))

                CreateProfileTextField(
                    value = state.surname,
                    onValueChange = { newValue ->
                        handleAction(CreateProfileAction.ChangeSurname(newValue))
                    },
                    placeholderText = stringResource(id = R.string.placeholder_surname)
                )
            }
        }

        Spacer(modifier = Modifier.height(SpacingNormal100))

        ErrorText(text = state.createProfileError, visible = state.createProfileErrorVisible)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                handleAction(CreateProfileAction.CreateProfile())
            },
            modifier = Modifier
                .height(ButtonHeightLarge)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.button_create_profile))
        }
    }

    if (state.loading) {
        CreateProfileLoadingDialog(noInternetWarningVisible = state.noInternetWarningVisible)
    }
}

@ScreenPreview
@Composable
private fun CreateProfileScreenPreview() {
    ChatterThemeWithSurface {
        CreateProfileScreen(
            state = CreateProfileState.Base(),
            effect = flow {},
            handleAction = {},
            navigateToChats = {}
        )
    }
}