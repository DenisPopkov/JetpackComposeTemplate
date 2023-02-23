package com.compose.template.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.compose.template.ui.components.TabItem
import com.compose.template.ui.screens.HomeScreen

fun NavGraphBuilder.homeNavGraph() {
    navigation(route = TabItem.HomeTab.route, startDestination = Screens.Home.route) {
        composable(Screens.Home.route) {
            HomeScreen()
        }
    }
}