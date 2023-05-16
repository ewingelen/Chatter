package com.ewingelen.chatter.auth.confirmCode.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.ewingelen.chatter.R
import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.auth.core.presentation.components.ErrorText
import com.ewingelen.chatter.core.presentation.BorderWidthMin
import com.ewingelen.chatter.core.presentation.ConfirmCodeCellSize
import com.ewingelen.chatter.core.presentation.Effect
import com.ewingelen.chatter.core.presentation.AuthScreenHeader
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.SpacingExtraLarge100
import com.ewingelen.chatter.core.presentation.SpacingLarge100
import com.ewingelen.chatter.core.presentation.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface
import com.ewingelen.chatter.core.presentation.theme.Gray500
import com.ewingelen.chatter.core.presentation.theme.Gray900
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConfirmCodeScreen(
    state: ConfirmCodeState,
    effect: Flow<Effect<HandleConfirmCodeEffect>>,
    handleAction: (ConfirmCodeAction) -> Unit,
    verifyPhoneNumber: (VerifyPhoneNumber) -> Unit,
    navigateToChats: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = Unit) {
        effect.collectLatest { effect ->
            val handleEffect = object : HandleConfirmCodeEffect {
                override fun authSuccess() {
                    navigateToChats()
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }

                override fun resentCode(verify: VerifyPhoneNumber) {
                    verifyPhoneNumber(verify)
                }
            }
            effect.handle(handleEffect)
        }
    }

    Column(
        modifier = Modifier
            .padding(
                start = SpacingNormal100,
                top = SpacingExtraLarge100,
                end = SpacingNormal100,
                bottom = SpacingLarge100
            )
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthScreenHeader(
            titleResourceId = R.string.title_enter_code,
            subtitle = stringResource(id = R.string.subtitle_format_enter_code, state.phoneNumber)
        )

        Spacer(modifier = Modifier.height(SpacingNormal100))

        BasicTextField(
            value = state.code,
            onValueChange = { handleAction(ConfirmCodeAction.ChangeCode(it)) },
            decorationBox = {
                Row(horizontalArrangement = Arrangement.spacedBy(SpacingNormal100)) {
                    val smsCodeLength = 6
                    repeat(smsCodeLength) { index ->
                        val char =
                            if (index >= state.code.length) "" else state.code[index].toString()
                        Box(
                            modifier = Modifier
                                .size(ConfirmCodeCellSize)
                                .border(
                                    width = BorderWidthMin,
                                    color = Gray500,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = char,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            enabled = !state.loading
        )

        Spacer(modifier = Modifier.height(SpacingNormal100))

        ErrorText(text = state.error)

        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            onClick = { handleAction(ConfirmCodeAction.ResentCode()) },
            enabled = state.resentCodeEnabled
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Gray900)) {
                        append(text = stringResource(id = R.string.label_did_not_get_code))
                    }
                    pushStyle(ParagraphStyle())
                    pushStyle(SpanStyle(color = MaterialTheme.colorScheme.onSurface))
                    append(text = state.timeToResentLabel)
                },
                textAlign = TextAlign.Center
            )
        }
    }
}

@ScreenPreview
@Composable
private fun ConfirmCodeScreenPreview() {
    ChatterThemeWithSurface {
        ConfirmCodeScreen(
            state = ConfirmCodeState(),
            effect = flow {},
            handleAction = {},
            verifyPhoneNumber = {},
            navigateToChats = {}
        )
    }
}