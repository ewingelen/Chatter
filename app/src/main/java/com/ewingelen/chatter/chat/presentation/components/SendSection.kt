package com.ewingelen.chatter.chat.presentation.components

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.domain.model.Message
import com.ewingelen.chatter.core.presentation.ComponentPreview
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.SpacingNormal100
import com.ewingelen.chatter.core.presentation.theme.SpacingSmall100

@Composable
fun SendSection(
    message: String,
    onMessageChange: (String) -> Unit,
    onSendMessage: () -> Unit,
    onAttachFiles: (List<Uri>) -> Unit,
    editingMessage: Message?,
    onEditingFinished: () -> Unit,
    onEditingCanceled: () -> Unit,
    modifier: Modifier = Modifier
) {
    val attachFile = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            result.data?.clipData?.let { clipData ->
                val uris = mutableListOf<Uri>()
                for (i in 0 until clipData.itemCount) {
                    val uri = clipData.getItemAt(i).uri
                    uris.add(uri)
                }
                onAttachFiles(uris)
            }
            result.data?.data?.let { uri ->
                onAttachFiles(listOf(uri))
            }
        }
    )

    ElevatedCard(
        shape = MaterialTheme.shapes.large.copy(
            bottomStart = ZeroCornerSize,
            bottomEnd = ZeroCornerSize
        ),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(SpacingNormal100)) {
            AnimatedVisibility(visible = editingMessage != null) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.Edit,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(SpacingNormal100))

                        Column {
                            Text(
                                text = stringResource(id = R.string.label_editing),
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )

                            editingMessage?.let {
                                Text(
                                    text = editingMessage.text,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(onClick = onEditingCanceled) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = stringResource(id = R.string.accessibility_cancel_editing)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(SpacingSmall100))

                    Divider()

                    Spacer(modifier = Modifier.height(SpacingSmall100))
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = message,
                    onValueChange = onMessageChange,
                    placeholder = {
                        Text(text = stringResource(id = R.string.placeholder_write_message))
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent
                    ),
                    modifier = Modifier.weight(1f)
                )

                AnimatedVisibility(visible = editingMessage != null) {
                    IconButton(onClick = onEditingFinished) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = stringResource(id = R.string.accessibility_edit_message),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                AnimatedVisibility(visible = editingMessage == null) {
                    IconButton(
                        onClick = {
                            Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                                type = "*/*"
                                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                                attachFile.launch(this)
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_attach_file),
                            contentDescription = stringResource(id = R.string.accessibility_attach_file)
                        )
                    }
                }

                AnimatedVisibility(visible = message.isNotBlank() && editingMessage == null) {
                    Spacer(modifier = Modifier.width(SpacingNormal100))

                    FilledIconButton(onClick = onSendMessage) {
                        Icon(
                            imageVector = Icons.Rounded.Send,
                            contentDescription = stringResource(id = R.string.accessibility_send_message),
                        )
                    }
                }
            }
        }
    }
}

@ComponentPreview
@Composable
private fun SendSectionPreview() {
    ChatterTheme {
        SendSection(
            message = "",
            onMessageChange = {},
            onSendMessage = {},
            onAttachFiles = {},
            editingMessage = null,
            onEditingFinished = {},
            onEditingCanceled = {}
        )
    }
}