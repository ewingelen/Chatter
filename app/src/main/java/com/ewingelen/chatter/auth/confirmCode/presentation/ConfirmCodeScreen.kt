package com.ewingelen.chatter.auth.confirmCode.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ewingelen.chatter.R
import com.ewingelen.chatter.auth.confirmCode.presentation.components.CodeTextField
import com.ewingelen.chatter.auth.confirmCode.presentation.components.ResentCodeTimerTextButton
import com.ewingelen.chatter.auth.confirmCode.presentation.contract.ConfirmCodeAction
import com.ewingelen.chatter.auth.confirmCode.presentation.contract.ConfirmCodeState
import com.ewingelen.chatter.auth.confirmCode.presentation.contract.HandleConfirmCodeEffect
import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.core.presentation.Effect
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.components.ErrorText
import com.ewingelen.chatter.core.presentation.components.ScreenHeader
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface
import com.ewingelen.chatter.core.presentation.theme.SpacingLarge100
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow


@Composable
fun ConfirmCodeScreen(
    state: ConfirmCodeState,
    effect: Flow<Effect<HandleConfirmCodeEffect>>,
    handleAction: (ConfirmCodeAction) -> Unit,
    navigateToCreateProfile: () -> Unit,
    navigateToChats: () -> Unit,
    verifyPhoneNumber: (VerifyPhoneNumber) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        val handleEffect = object : HandleConfirmCodeEffect {
            override fun successSignUp() {
                navigateToCreateProfile()
            }

            override fun successLogIn() {
                navigateToChats()
            }

            override fun codeResent(verify: VerifyPhoneNumber) {
                verifyPhoneNumber(verify)
            }
        }
        effect.collectLatest { effect ->
            effect.handle(handleEffect)
        }
    }

    Column(
        modifier = Modifier
            .padding(horizontal = SpacingNormal100, vertical = SpacingLarge100)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader(
            titleResourceId = R.string.title_enter_code,
            subtitle = stringResource(id = R.string.subtitle_format_enter_code, state.phoneNumber)
        )

        Spacer(modifier = Modifier.height(SpacingNormal100))

        CodeTextField(
            value = state.code,
            onValueChange = { handleAction(ConfirmCodeAction.ChangeCode(it)) },
            enabled = !state.loading
        )

        Spacer(modifier = Modifier.height(SpacingNormal100))

        ErrorText(text = state.error, visible = state.errorVisible)

        Spacer(modifier = Modifier.weight(1f))

        ResentCodeTimerTextButton(
            onClick = { handleAction(ConfirmCodeAction.ResentCode()) },
            enabled = state.resentCodeEnabled,
            timeToResentLabel = state.timeToResent
        )
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
            navigateToCreateProfile = {},
            navigateToChats = {}
        )
    }
}