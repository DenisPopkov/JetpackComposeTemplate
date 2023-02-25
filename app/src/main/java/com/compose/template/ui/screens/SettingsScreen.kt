package com.compose.template.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(onProfileSelected: () -> Unit, onBackPressed: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Button(onClick = {
            onProfileSelected.invoke()
        }) {
            Text(
                "Settings",
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        BackHandler(onBack = {
            onBackPressed()
        })
    }
}

@Composable
fun ProfileScreen() {
    Column(Modifier.fillMaxSize()) {
        Text(
            "ProfileScreen",
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}