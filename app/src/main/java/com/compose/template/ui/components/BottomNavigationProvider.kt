package com.compose.template.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.compose.template.R

sealed class TabItem(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
) {
    object HomeTab : TabItem("home_tab", Icons.Filled.Home, Icons.Outlined.Home, R.string.home)
    object SettingsTab :
        TabItem("settings_tab", Icons.Filled.Settings, Icons.Outlined.Settings, R.string.settings)
}

private val tabItems = listOf(
    TabItem.HomeTab,
    TabItem.SettingsTab,
)

@Composable
fun BottomNavScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val selectionMap = remember(currentDestination) {
        tabItems.associateWith { tabItem ->
            (currentDestination?.hierarchy?.any { it.route == tabItem.route } == true)
        }
    }

    NavigationBar(modifier = modifier) {
        tabItems.forEach { tabItem ->
            val selected = selectionMap.getOrDefault(tabItem, false)
            NavigationBarItem(
                selected = selected,
                onClick = { navigate(navController, tabItem.route) },
                icon = {
                    val icon = if (selected) {
                        tabItem.selectedIcon
                    } else {
                        tabItem.unselectedIcon
                    }
                    Icon(imageVector = icon, contentDescription = null)
                },
                label = {
                    Text(
                        text = stringResource(tabItem.iconTextId),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
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