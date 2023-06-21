package com.ewingelen.chatter.call.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.presentation.theme.Black900
import com.ewingelen.chatter.core.presentation.theme.White900
import io.agora.rtc.RtcEngine

@Composable
fun CallActions(
    videoDisabled: Boolean,
    onVideoToggle: () -> Unit,
    mEngine: RtcEngine,
    navigateUp: () -> Unit
) {
    var muted by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        FilledIconButton(
            onClick = {
                muted = !muted
                mEngine.muteLocalAudioStream(muted)
            },
            modifier = Modifier.size(50.dp),
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = Black900)
        ) {
            AnimatedVisibility(
                visible = muted,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_mic_off),
                    contentDescription = stringResource(id = R.string.accessibility_unmute_mic),
                    tint = Color.Unspecified
                )
            }

            AnimatedVisibility(
                visible = !muted,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_mic_on),
                    contentDescription = stringResource(id = R.string.accessibility_mute_mic),
                    tint = Color.Unspecified
                )
            }
        }

        FilledIconButton(
            onClick = {
                mEngine.leaveChannel()
                navigateUp()
            },
            modifier = Modifier.size(70.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = White900
            )
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = stringResource(id = R.string.accessibility_disconnect_call)
            )
        }

        FilledIconButton(
            onClick = {
                onVideoToggle()
                mEngine.enableLocalAudio(videoDisabled)
                mEngine.muteLocalVideoStream(videoDisabled)
            },
            modifier = Modifier.size(50.dp),
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = Black900)
        ) {
            AnimatedVisibility(
                visible = videoDisabled,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera_off),
                    contentDescription = stringResource(id = R.string.accessibility_enable_video),
                    tint = Color.Unspecified
                )
            }

            AnimatedVisibility(
                visible = !videoDisabled,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera_on),
                    contentDescription = stringResource(id = R.string.accessibility_diable_video),
                    tint = Color.Unspecified
                )
            }
        }
    }
}