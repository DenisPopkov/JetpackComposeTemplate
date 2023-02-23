package com.compose.template.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.compose.template.ui.components.TabItem

@Composable
fun NavGraph(navController: NavHostController) {

    val actions = remember(navController) { NavigationActions(navController) }

    NavHost(navController = navController, startDestination = TabItem.HomeTab.route) {
        homeNavGraph()
        settingsNavGraph(actions)
    }
}