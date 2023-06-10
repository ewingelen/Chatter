package com.ewingelen.chatter.auth.createProfile.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall100

@Composable
fun CreateProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable () -> Unit = {},
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onBackground
        ),
        cursorBrush = Brush.verticalGradient(
            listOf(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.primary
            )
        ),
        modifier = modifier
    ) { innerTextField ->
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box {
                    if (value.isEmpty()) {
                        Text(text = placeholderText)
                    }

                    innerTextField()
                }

                trailingIcon()
            }

            Spacer(modifier = Modifier.height(SpacingSmall100))

            Divider(color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview
@Composable
private fun CreateProfileTextFieldPreview() {
    CreateProfileTextField(
        value = "",
        placeholderText = stringResource(id = R.string.placeholder_surname),
        onValueChange = {}
    )
}