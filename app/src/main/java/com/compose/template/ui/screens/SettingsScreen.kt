package com.compose.template.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(onProfileSelected: () -> Unit, onBackPressed: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Button(onClick = {
            onProfileSelected.invoke()
        }) {
            Text("Settings")
        }
        BackHandler(onBack = {
            onBackPressed()
        })
    }
}

@Composable
fun ProfileScreen() {
    Column(Modifier.fillMaxSize()) {
        Text("ProfileScreen")
    }
}