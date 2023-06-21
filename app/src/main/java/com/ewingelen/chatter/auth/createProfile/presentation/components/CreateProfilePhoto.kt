package com.ewingelen.chatter.auth.createProfile.presentation.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.ewingelen.chatter.core.presentation.theme.BorderWidthExtraSmall
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.ProfilePhotoSize

@Composable
fun CreateProfilePhoto(
    addPhoto: (Uri) -> Unit,
    photo: Uri,
    modifier: Modifier = Modifier
) {
    val addPhotoPlaceholderPainter = painterResource(id = R.drawable.img_add_avatar)
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let { addPhoto(uri) }
        }
    )

    Box {
        AsyncImage(
            model = photo,
            contentDescription = stringResource(id = R.string.accessibility_add_profile_photo),
            fallback = addPhotoPlaceholderPainter,
            placeholder = addPhotoPlaceholderPainter,
            error = addPhotoPlaceholderPainter,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(ProfilePhotoSize)
                .border(
                    width = BorderWidthExtraSmall,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .clickable {
                    val pickVisualMediaRequest =
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    photoPickerLauncher.launch(pickVisualMediaRequest)
                }
        )

        Icon(
            imageVector = Icons.Rounded.AddCircle,
            contentDescription = stringResource(id = R.string.accessibility_add_profile_photo),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview
@Composable
private fun CreateProfilePhotoPreview() {
    ChatterTheme {
        CreateProfilePhoto(photo = Uri.EMPTY, addPhoto = {})
    }
}