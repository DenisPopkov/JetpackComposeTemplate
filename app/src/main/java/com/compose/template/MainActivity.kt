package com.compose.template

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.compose.template.datastore.ThemeViewModel
import com.compose.template.navigation.NavGraph
import com.compose.template.ui.components.BottomNavScreen
import com.compose.template.ui.theme.apptheme.AppTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

val Context.dataStore by preferencesDataStore("settings")

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)

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

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(bottomBar = { BottomNavScreen(navController) }) { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                    NavGraph(navController)
                }
            }
        }
    }
}