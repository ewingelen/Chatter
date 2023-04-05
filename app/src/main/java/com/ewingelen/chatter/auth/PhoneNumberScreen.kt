package com.ewingelen.chatter.auth

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.*
import com.ewingelen.chatter.core.presentation.theme.Gray500
import com.ewingelen.chatter.core.presentation.theme.ScreenTheme

@Composable
fun AuthScreen(navigateToCode: () -> Unit) {

    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(
            start = SpacingNormal100,
            top = SpacingExtraLarge100,
            end = SpacingNormal100,
            bottom = SpacingLarge100
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.label_enter_phone_number),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(SpacingNormal100))

        Text(
            text = stringResource(id = R.string.label_confirm_region),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(SpacingLarge100))

        BasicTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            modifier = Modifier
                .height(ButtonHeightLarge)
                .border(color = Gray500, shape = CircleShape, width = 1.dp)
                .padding(horizontal = SpacingNormal100)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
        ) { innerTextField ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(SpacingNormal100))

                Box {
                    innerTextField()

                    if (phoneNumber.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.hint_phone_number),
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = navigateToCode,
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
fun AuthScreenPreview() {
    ScreenTheme {
        AuthScreen(navigateToCode = {})
    }
}