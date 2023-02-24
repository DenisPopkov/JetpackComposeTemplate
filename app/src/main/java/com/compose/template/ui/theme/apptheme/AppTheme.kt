package com.compose.template.ui.theme.apptheme

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.compose.template.dataStore
import com.compose.template.datastore.ThemeViewModel
import com.compose.template.ui.theme.color.DarkColorScheme
import com.compose.template.ui.theme.color.LightColorScheme
import com.compose.template.ui.theme.typography.LocalTypography
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val viewModel = remember { ThemeViewModel(context.dataStore) }
    val state = viewModel.state.observeAsState()
    val value = state.value ?: isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(viewModel) { viewModel.request() }
    DarkThemeValue.current.value = value

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    DisposableEffect(systemUiController, darkTheme) {
        systemUiController.setSystemBarsColor(
            color = colorScheme.surface,
            darkIcons = !darkTheme
        )

        onDispose { }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = LocalTypography.current.Typography,
        content = content
    )
}

@SuppressLint("CompositionLocalNaming")
private val DarkThemeValue = compositionLocalOf { mutableStateOf(false) }