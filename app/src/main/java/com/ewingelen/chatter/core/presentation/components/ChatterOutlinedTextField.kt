package com.ewingelen.chatter.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.ComponentPreview
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
@Composable
fun ChatterOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector,
    @StringRes labelResourceId: Int,
    @StringRes placeholderResourceId: Int,
    modifier: Modifier = Modifier,
    prefix: @Composable () -> Unit = {},
    supportingText: @Composable () -> Unit = {},
    singleLine: Boolean = false,
    isError: Boolean = false,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(imageVector = leadingIcon, contentDescription = null)
        },
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
            leadingIcon = Icons.Rounded.Phone,
            labelResourceId = R.string.label_phone_number,
            placeholderResourceId = R.string.placeholder_enter_contact_phone_number,
        )
    }
}