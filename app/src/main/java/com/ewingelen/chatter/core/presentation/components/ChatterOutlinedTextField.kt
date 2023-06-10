package com.ewingelen.chatter.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.ComponentPreview
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme

//TODO: Remove
@Composable
fun ChatterOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes labelResourceId: Int,
    @StringRes placeholderResourceId: Int,
    modifier: Modifier = Modifier,
    prefix: @Composable () -> Unit = {},
    leadingIcon: (@Composable () -> Unit)? = null,
    supportingText: @Composable () -> Unit = {},
    singleLine: Boolean = false,
    isError: Boolean = false,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = leadingIcon,
        label = {
            Text(text = stringResource(id = labelResourceId))
        },
        placeholder = {
            Text(text = stringResource(id = placeholderResourceId))
        },
        prefix = prefix,
        isError = isError,
        supportingText = supportingText,
        singleLine = singleLine,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

@ComponentPreview
@Composable
fun ChatterTextFieldPreview() {
    ChatterTheme {
        ChatterOutlinedTextField(
            value = "",
            onValueChange = {},
            leadingIcon = {},
            labelResourceId = R.string.label_phone_number,
            placeholderResourceId = R.string.placeholder_enter_contact_phone_number,
        )
    }
}