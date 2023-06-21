package com.ewingelen.chatter.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.theme.Black900
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.ContactPhotoSize

@Composable
fun ContactPhoto(
    photo: String,
    name: String,
    surname: String,
    modifier: Modifier = Modifier
) {
    if (photo.isNotEmpty()) {
        val placeholder = painterResource(id = R.drawable.ic_person)
        AsyncImage(
            model = photo,
            contentDescription = stringResource(id = R.string.accessibility_contact_photo),
            placeholder = placeholder,
            error = placeholder,
            fallback = placeholder,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(ContactPhotoSize)
                .clip(shape = MaterialTheme.shapes.small)
                .background(color = MaterialTheme.colorScheme.primary)
        )
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(ContactPhotoSize)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.small
                )
        ) {
            Text(
                text = "${if (name.isNotEmpty()) name.first() else ""} ${if (surname.isNotEmpty()) surname.first() else ""}",
                style = MaterialTheme.typography.labelLarge,
                color = Black900
            )
        }
    }
}

@Preview
@Composable
private fun ContactPhotoPreview() {
    ChatterTheme {
        ContactPhoto(
            photo = "",
            name = "Artem",
            surname = "Wilnow"
        )
    }
}