package com.compose.template.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun FloatingButton(onClick: () -> Unit) {
    LargeFloatingActionButton(
        onClick = { onClick.invoke() },
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null, tint = MaterialTheme.colorScheme.background)
    }
}