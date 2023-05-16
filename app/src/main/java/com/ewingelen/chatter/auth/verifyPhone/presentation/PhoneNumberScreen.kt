package com.ewingelen.chatter.auth.verifyPhone.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.ewingelen.chatter.R
import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.auth.core.presentation.components.ErrorText
import com.ewingelen.chatter.core.presentation.ButtonHeightLarge
import com.ewingelen.chatter.core.presentation.components.ChatterOutlinedTextField
import com.ewingelen.chatter.core.presentation.AuthScreenHeader
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.SpacingExtraLarge100
import com.ewingelen.chatter.core.presentation.SpacingLarge100
import com.ewingelen.chatter.core.presentation.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PhoneNumberScreen(
    state: PhoneNumberState,
    effect: Flow<PhoneNumberEffect>,
    handleAction: (PhoneNumberAction) -> Unit,
    verifyPhoneNumber: (VerifyPhoneNumber) -> Unit,
    navigateToConfirmCode: (verificationId: String, phoneNumber: String) -> Unit,
    navigateToChats: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = Unit) {
        val handleEffect = object : HandlePhoneNumberEffect {
            override fun startVerification(verify: VerifyPhoneNumber) {
                verifyPhoneNumber(verify)
            }

            override fun completeVerification() {
                navigateToChats()
            }

            override fun navigateToTheCodeScreen(verificationId: String, phoneNumber: String) {
                navigateToConfirmCode(verificationId, phoneNumber)
            }
        }
        effect.collectLatest { effect ->
            effect.handle(handleEffect)
        }
    }

    Column(
        modifier = Modifier.padding(
            start = SpacingNormal100,
            top = SpacingExtraLarge100,
            end = SpacingNormal100,
            bottom = SpacingLarge100
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthScreenHeader(
            titleResourceId = R.string.title_enter_phone_number,
            subtitle = stringResource(id = R.string.subtitle_enter_phone_number)
        )

        Spacer(modifier = Modifier.height(SpacingLarge100))

        ChatterOutlinedTextField(
            value = state.phoneNumber,
            onValueChange = { handleAction(PhoneNumberAction.ChangePhoneNumber(it)) },
            leadingIcon = Icons.Rounded.Add,
            labelResourceId = R.string.label_phone_number,
            placeholderResourceId = R.string.placeholder_enter_contact_phone_number,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            enabled = !state.loading,
            modifier = Modifier.fillMaxWidth()
        )

        ErrorText(text = state.error)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                handleAction(PhoneNumberAction.SignUp())
                keyboardController?.hide()
                focusManager.clearFocus()
            },
            enabled = !state.loading,
            modifier = Modifier
                .fillMaxWidth()
                .height(ButtonHeightLarge)
        ) {
            Text(text = stringResource(id = R.string.button_continue))
        }
    }
}

@ScreenPreview
@Composable
private fun AuthScreenPreview() {
    ChatterThemeWithSurface {
        PhoneNumberScreen(
            state = PhoneNumberState(),
            effect = flow {},
            handleAction = {},
            verifyPhoneNumber = {},
            navigateToConfirmCode = { _, _ -> },
            navigateToChats = {},
        )
    }
}