package com.compose.template.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.compose.template.ui.theme.apptheme.AppTheme
import com.compose.template.utills.calculateIndicatorColor

@Composable
fun SettingsScreen(onProfileSelected: () -> Unit, onBackPressed: () -> Unit) {
    AppTheme {
        Column(Modifier.fillMaxSize()) {
            Button(
                onClick = {
                    onProfileSelected.invoke()
                },
                colors = ButtonDefaults.buttonColors(containerColor = calculateIndicatorColor())
            ) {
                Text(
                    "Settings",
                    color = MaterialTheme.colorScheme.surface
                )
            }
            BackHandler(onBack = {
                onBackPressed()
            })
        }
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