package com.compose.template

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import com.compose.template.datastore.ThemeViewModel
import com.compose.template.navigation.NavGraph
import com.compose.template.ui.components.BottomNavScreen
import com.compose.template.ui.theme.apptheme.AppTheme

val Context.dataStore by preferencesDataStore("settings")

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    AppTheme {

        val context = LocalContext.current
        val navController = rememberNavController()

        val viewModel = remember {
            ThemeViewModel(context.dataStore)
        }

        val value = viewModel.state.observeAsState().value
        val systemInDarkTheme = isSystemInDarkTheme()

        val darkModeChecked by remember(value) {
            val checked = when (value) {
                null -> systemInDarkTheme
                else -> value
            }
            mutableStateOf(checked)
        }

        LaunchedEffect(viewModel) {
            viewModel.request()
        }

        Surface(modifier = Modifier.background(MaterialTheme.colors.background).fillMaxSize()) {
            SetStatusBarColor()
            Scaffold(bottomBar = { BottomNavScreen(navController) }) { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                    NavGraph(navController)
                }
            }
        }
    }
}

@Composable
fun SetStatusBarColor() {
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.colors.background

    SideEffect {
        systemUiController.setStatusBarColor(color = color)
    }
}