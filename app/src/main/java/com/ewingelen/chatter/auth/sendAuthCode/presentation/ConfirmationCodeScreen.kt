package com.ewingelen.chatter.auth.sendAuthCode.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import com.ewingelen.chatter.R
import com.ewingelen.chatter.auth.core.presentation.components.AuthHeader
import com.ewingelen.chatter.core.presentation.BorderWidthMin
import com.ewingelen.chatter.core.presentation.ConfirmationCodeCellSize
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.SpacingExtraLarge100
import com.ewingelen.chatter.core.presentation.SpacingLarge100
import com.ewingelen.chatter.core.presentation.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.Gray500
import com.ewingelen.chatter.core.presentation.theme.Gray900
import com.ewingelen.chatter.core.presentation.theme.ScreenTheme

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@Composable
fun ConfirmationCodeScreen(navigateToMain: () -> Unit) {
    var code by remember { mutableStateOf("") }

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
            titleResourceId = R.string.label_enter_code,
            subtitle = stringResource(id = R.string.label_code_sent, "380660164667")
        )

        Spacer(modifier = Modifier.height(SpacingNormal100))

        BasicTextField(
            value = code,
            onValueChange = { code = it },
            decorationBox = {
                Row(horizontalArrangement = Arrangement.spacedBy(SpacingNormal100)) {
                    val smsCodeLength = 6
                    repeat(smsCodeLength) { index ->
                        //TODO: check on small width device
                        val char = if (index >= code.length) "" else code[index].toString()
                        Box(
                            modifier = Modifier
                                .size(ConfirmationCodeCellSize)
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )

        Spacer(modifier = Modifier.weight(1f))

        TextButton(onClick = {}) {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Gray900)) {
                        append(stringResource(id = R.string.label_did_not_get_code))
                    }

                    append(" ")

                    append(stringResource(id = R.string.label_clickable_resent_code))
                }
            )
        }
    }
}

@ScreenPreview
@Composable
private fun ConfirmationCodeScreenPreview() {
    ScreenTheme {
        ConfirmationCodeScreen(navigateToMain = {})
    }
}