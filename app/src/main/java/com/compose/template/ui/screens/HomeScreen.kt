package com.compose.template.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() {
    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Home",
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}