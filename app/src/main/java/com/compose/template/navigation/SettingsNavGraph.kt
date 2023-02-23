package com.compose.template.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.compose.template.ui.components.TabItem
import com.compose.template.ui.screens.ProfileScreen
import com.compose.template.ui.screens.SettingsScreen

fun NavGraphBuilder.settingsNavGraph(actions: NavigationActions) {
    navigation(route = TabItem.SettingsTab.route, startDestination = Screens.Settings.route) {
        composable(Screens.Settings.route) {
            SettingsScreen(onProfileSelected = {
                actions.navigateTo(Screens.ProfileSettings.route)
            }, onBackPressed = {
                actions.newRootScreen(Screens.Settings.route)
            })
        }
        composable(Screens.ProfileSettings.route) {
            ProfileScreen()
        }
    }
}