package com.ewingelen.chatter.createProfile.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.ButtonHeightLarge
import com.ewingelen.chatter.core.presentation.CreateProfilePhotoSize
import com.ewingelen.chatter.core.presentation.ScreenHeader
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.SpacingLarge100
import com.ewingelen.chatter.core.presentation.SpacingNormal100
import com.ewingelen.chatter.core.presentation.components.ChatterOutlinedTextField
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface

@Composable
fun CreateProfileScreen(
    state: CreateProfileState,
    handleAction: (CreateProfileAction) -> Unit
) {
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let { handleAction(CreateProfileAction.AddPhoto(uri)) }
        }
    )

    Column(
        modifier = Modifier.padding(horizontal = SpacingNormal100, vertical = SpacingLarge100),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader(
            titleResourceId = R.string.title_create_profile,
            subtitle = stringResource(id = R.string.subtitle_create_profile)
        )

        Spacer(modifier = Modifier.height(SpacingNormal100))

        val addPhotoPlaceholderPainter = painterResource(id = R.drawable.img_add_photo_placeholder)

        AsyncImage(
            model = state.photo,
            contentDescription = stringResource(id = R.string.accessibility_profile_photo),
            fallback = addPhotoPlaceholderPainter,
            placeholder = addPhotoPlaceholderPainter,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(CreateProfilePhotoSize)
                .clip(MaterialTheme.shapes.small)
                .clickable {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
        )

        Spacer(modifier = Modifier.height(SpacingNormal100))

        ChatterOutlinedTextField(
            value = state.name,
            onValueChange = { handleAction(CreateProfileAction.ChangeName(it)) },
            leadingIcon = Icons.Default.Person,
            labelResourceId = R.string.label_name,
            placeholderResourceId = R.string.placeholder_enter_your_name,
            supportingText = {
                if (state.errorVisible) {
                    Text(text = stringResource(id = R.string.error_empty_name))
                }
            },
            isError = state.errorVisible,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { handleAction(CreateProfileAction.CreateProfile()) },
            modifier = Modifier
                .height(ButtonHeightLarge)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.button_create_profile))
        }
    }
}

@ScreenPreview
@Composable
private fun CreateProfileScreenPreview() {
    ChatterThemeWithSurface {
        CreateProfileScreen(
            state = CreateProfileState(),
            handleAction = {}
        )
    }
}