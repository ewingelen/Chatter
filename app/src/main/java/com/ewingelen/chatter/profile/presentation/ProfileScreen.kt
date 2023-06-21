package com.ewingelen.chatter.profile.presentation

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import coil.compose.AsyncImage
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.ScreenPreview
import com.ewingelen.chatter.core.presentation.theme.BorderWidthExtraSmall
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface
import com.ewingelen.chatter.core.presentation.theme.ProfilePhotoSize
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.White900
import com.ewingelen.chatter.profile.presentation.contract.ProfileAction
import com.ewingelen.chatter.profile.presentation.contract.ProfileState

@Composable
fun ProfileScreen(
    state: ProfileState,
    handleAction: (ProfileAction) -> Unit,
    showSnackbar: (String) -> Unit,
    navigateToOnBoarding: () -> Unit,
    modifier: Modifier = Modifier
) {
    val clipboardManager = LocalClipboardManager.current
    val copiedSnackbarLabel = stringResource(id = R.string.snackbar_phone_number_copied)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(SpacingNormal100))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(SpacingNormal100)
        ) {
            val placeholder = painterResource(id = R.drawable.img_add_avatar)
            AsyncImage(
                model = state.photo,
                contentDescription = stringResource(id = R.string.accessibility_your_profile_photo),
                contentScale = ContentScale.Crop,
                placeholder = placeholder,
                error = placeholder,
                fallback = placeholder,
                modifier = Modifier
                    .size(ProfilePhotoSize)
                    .clip(shape = CircleShape)
                    .background(White900)
                    .border(
                        width = BorderWidthExtraSmall,
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.width(SpacingNormal100))

            Column {
                Text(text = state.name, style = MaterialTheme.typography.headlineMedium)

                if (state.surname.isNotEmpty()) {
                    Text(text = state.surname, style = MaterialTheme.typography.labelLarge)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = {
                    handleAction(ProfileAction.SignOut())
                    navigateToOnBoarding()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(SpacingNormal100))

        Divider(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))

        ListItem(
            leadingContent = {
                Icon(
                    imageVector = Icons.Rounded.Phone,
                    contentDescription = null
                )
            },
            headlineContent = {
                Text(
                    text = state.phoneNumber,
                    modifier = Modifier.padding(horizontal = SpacingNormal100)
                )
            },
            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
            modifier = Modifier.clickable {
                clipboardManager.setText(AnnotatedString(state.phoneNumber))
                if (Build.VERSION.SDK_INT != Build.VERSION_CODES.TIRAMISU) {
                    showSnackbar(copiedSnackbarLabel)
                }
            }
        )

        Divider(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
    }
}

@ScreenPreview
@Composable
private fun ProfileScreenPreview() {
    ChatterThemeWithSurface {
        ProfileScreen(
            state = ProfileState(),
            handleAction = {},
            showSnackbar = {},
            navigateToOnBoarding = {}
        )
    }
}

