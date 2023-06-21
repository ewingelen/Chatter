package com.ewingelen.chatter.chat.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ewingelen.chatter.R

@Composable
fun MessageDropdownMenu(
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    deleteAvailable: Boolean,
    editAvailable: Boolean,
    modifier: Modifier = Modifier
) {
    if(deleteAvailable || editAvailable) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            modifier = modifier
        ) {
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                },
                text = {
                    Text(text = stringResource(id = R.string.menu_item_delete))
                },
                onClick = onDelete
            )

            if (editAvailable) {
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null
                        )
                    },
                    text = {
                        Text(text = stringResource(id = R.string.menu_item_edit))
                    },
                    onClick = onEdit
                )
            }
        }
    }
}