package com.ewingelen.chatter.auth.confirmCode.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.core.presentation.theme.BorderWidthMin
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.ConfirmCodeCellSize
import com.ewingelen.chatter.core.presentation.theme.Gray500
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100

@Composable
fun CodeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        decorationBox = {
            Row(horizontalArrangement = Arrangement.spacedBy(SpacingNormal100)) {
                val smsCodeLength = 6
                repeat(smsCodeLength) { index ->
                    val char = if (index >= value.length) "" else value[index].toString()
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
        enabled = enabled,
        modifier = modifier
    )
}

@Preview
@Composable
private fun CodeTextFieldPreview() {
    ChatterTheme {
        CodeTextField(
            value = "123",
            onValueChange = {},
            enabled = true
        )
    }
}