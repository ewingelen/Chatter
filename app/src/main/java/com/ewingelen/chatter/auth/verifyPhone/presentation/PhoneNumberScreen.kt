package com.ewingelen.chatter.auth.verifyPhone.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.ewingelen.chatter.R
import com.ewingelen.chatter.auth.confirmCode.presentation.ConfirmCodeArgs
import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.auth.verifyPhone.presentation.contract.HandlePhoneNumberEffect
import com.ewingelen.chatter.auth.verifyPhone.presentation.contract.PhoneNumberAction
import com.ewingelen.chatter.auth.verifyPhone.presentation.contract.PhoneNumberEffect
import com.ewingelen.chatter.auth.verifyPhone.presentation.contract.PhoneNumberState
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.components.ChatterOutlinedTextField
import com.ewingelen.chatter.core.presentation.components.ErrorText
import com.ewingelen.chatter.core.presentation.components.ScreenHeader
import com.ewingelen.chatter.core.presentation.theme.ButtonHeightLarge
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface
import com.ewingelen.chatter.core.presentation.theme.SpacingLarge100
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

@Composable
fun PhoneNumberScreen(
    state: PhoneNumberState,
    effect: Flow<PhoneNumberEffect>,
    handleAction: (PhoneNumberAction) -> Unit,
    verifyPhoneNumber: (VerifyPhoneNumber) -> Unit,
    navigateToConfirmCode: (ConfirmCodeArgs) -> Unit,
    navigateToAppSections: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        val handleEffect = object : HandlePhoneNumberEffect {
            override fun startVerification(verify: VerifyPhoneNumber) {
                verifyPhoneNumber(verify)
            }

            override fun continueVerification(verificationId: String, phoneNumber: String) {
                val args = ConfirmCodeArgs(verificationId, phoneNumber)
                navigateToConfirmCode(args)
            }

            override fun completeVerification() {
                navigateToAppSections()
            }
        }
        effect.collectLatest { effect ->
            effect.handle(handleEffect)
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = SpacingNormal100, vertical = SpacingLarge100),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader(
            titleResourceId = R.string.title_enter_phone_number,
            subtitle = stringResource(id = R.string.subtitle_enter_phone_number)
        )

        Spacer(modifier = Modifier.height(SpacingLarge100))

        ChatterOutlinedTextField(
            value = state.phoneNumber,
            onValueChange = { handleAction(PhoneNumberAction.ChangePhoneNumber(it)) },
            leadingIcon = { Icon(imageVector = Icons.Rounded.Phone, contentDescription = null) },
            labelResourceId = R.string.label_phone_number,
            placeholderResourceId = R.string.placeholder_enter_contact_phone_number,
            prefix = {
                Text(text = stringResource(id = R.string.symbol_plus))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            enabled = !state.loading,
            modifier = Modifier.fillMaxWidth()
        )

        ErrorText(text = state.error, visible = state.errorVisible)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { handleAction(PhoneNumberAction.SignUp()) },
            enabled = !state.loading,
            modifier = Modifier
                .height(ButtonHeightLarge)
                .fillMaxWidth()
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
            navigateToConfirmCode = { ConfirmCodeArgs("", "") },
            navigateToAppSections = {}
        )
    }
}