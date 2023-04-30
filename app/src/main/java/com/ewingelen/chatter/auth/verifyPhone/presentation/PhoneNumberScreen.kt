package com.ewingelen.chatter.auth.verifyPhone.presentation

import android.app.Activity
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.PreviewActivity
import com.ewingelen.chatter.R
import com.ewingelen.chatter.auth.core.presentation.components.AuthHeader
import com.ewingelen.chatter.auth.verifyPhone.presentation.components.LoadingFullscreen
import com.ewingelen.chatter.core.presentation.BorderWidthMin
import com.ewingelen.chatter.core.presentation.ButtonHeightLarge
import com.ewingelen.chatter.core.presentation.Effect
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.SpacingExtraLarge100
import com.ewingelen.chatter.core.presentation.SpacingExtraSmall100
import com.ewingelen.chatter.core.presentation.SpacingLarge100
import com.ewingelen.chatter.core.presentation.SpacingNormal100
import com.ewingelen.chatter.core.presentation.TextFieldHeightLarge
import com.ewingelen.chatter.core.presentation.findActivity
import com.ewingelen.chatter.core.presentation.theme.Gray500
import com.ewingelen.chatter.core.presentation.theme.ScreenTheme
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
    effect: Flow<Effect<HandlePhoneNumberEffect>>,
    handleAction: (PhoneNumberAction) -> Unit,
    navigateToCode: (verificationId: String) -> Unit,
    navigateToMain: () -> Unit,
    activity: Activity = LocalContext.current.findActivity()
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = effect) {
        effect.collectLatest { effect ->
            effect.handle(
                object : HandlePhoneNumberEffect {
                    override fun startVerification(verifyPhoneNumber: VerifyPhoneNumber) {
                        verifyPhoneNumber.verify(activity)
                    }

                    override fun completeVerification() {
                        navigateToMain()
                    }

                    override fun navigateToTheCodeScreen(verificationId: String) {
                        navigateToCode(verificationId)
                    }
                }
            )
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
        AuthHeader(
            titleResourceId = R.string.label_enter_phone_number,
            subtitle = stringResource(id = R.string.label_confirm_region)
        )

        Spacer(modifier = Modifier.height(SpacingLarge100))

        BasicTextField(
            value = state.phoneNumber,
            onValueChange = { newValue ->
                handleAction(PhoneNumberAction.ChangePhoneNumber(newValue))
            },
            modifier = Modifier
                .height(TextFieldHeightLarge)
                .border(color = Gray500, shape = CircleShape, width = BorderWidthMin)
                .padding(horizontal = SpacingNormal100)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface)
        ) { innerTextField ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.Phone,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(SpacingNormal100))

                if (state.phoneNumber.isNotEmpty()) {
                    Text(text = "+")

                    Spacer(modifier = Modifier.width(SpacingExtraSmall100))
                }

                Box {
                    innerTextField()

                    if (state.phoneNumber.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.hint_phone_number),
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(SpacingNormal100))

        Text(
            text = state.errorText,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.error,
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                handleAction(PhoneNumberAction.SignUp())
                keyboardController?.hide()
                focusManager.clearFocus()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(ButtonHeightLarge)
        ) {
            Text(text = stringResource(id = R.string.button_continue))
        }
    }

    if (state.isLoading) {
        LoadingFullscreen()
    }
}

@ScreenPreview
@Composable
private fun AuthScreenPreview() {
    ScreenTheme {
        PhoneNumberScreen(
            state = PhoneNumberState(),
            effect = flow {},
            handleAction = {},
            navigateToCode = {},
            navigateToMain = {},
            activity = PreviewActivity()
        )
    }
}