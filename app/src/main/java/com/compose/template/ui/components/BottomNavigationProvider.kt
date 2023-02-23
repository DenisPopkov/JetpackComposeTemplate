package com.compose.template.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class TabItem(val route: String, val icon: ImageVector) {
    object HomeTab : TabItem("home_tab", Icons.Default.Home)
    object SettingsTab : TabItem("settings_tab", Icons.Default.Settings)
}

private val tabItems = listOf(
    TabItem.HomeTab,
    TabItem.SettingsTab,
)

@Composable
fun BottomNavScreen(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val selectionMap = remember(currentDestination) {
        tabItems.associateWith { tabItem ->
            (currentDestination?.hierarchy?.any { it.route == tabItem.route } == true)
        }
    }
    BottomNavigation {
        tabItems.forEach { tabItem ->
            BottomNavigationItem(
                icon = { Icon(tabItem.icon, null) },
                selected = selectionMap.getOrDefault(tabItem, false),
                onClick = {
                    navigate(navController, tabItem.route)
                }
            )
        }
    }
}

private fun navigate(navController: NavHostController, route: String) {
    navController.navigate(route) {
        val navigationRoutes = tabItems.map { it.route }
        val firstBottomBarDestination = navController.backQueue.firstOrNull {
            navigationRoutes.contains(it.destination.route)
        }?.destination
        if (firstBottomBarDestination != null) {
            popUpTo(firstBottomBarDestination.id) {
                inclusive = true
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}