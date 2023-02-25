package com.compose.template.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.compose.template.R

sealed class TabItem(
    val route: String,
    val icon: ImageVector,
    val iconTextId: Int
) {
    object HomeTab : TabItem("home_tab", Icons.Outlined.Home, R.string.home)
    object SettingsTab :
        TabItem("settings_tab", Icons.Outlined.Settings, R.string.settings)
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

    val navigationBarItemColors = NavigationBarItemDefaults.colors(
        indicatorColor = getIndicatorColor(),
        selectedTextColor = MaterialTheme.colorScheme.onSurface
    )

    NavigationBar(modifier = modifier.height(100.dp)) {
        tabItems.forEach { tabItem ->
            val selected = selectionMap.getOrDefault(tabItem, false)
            NavigationBarItem(
                selected = selected,
                colors = navigationBarItemColors,
                onClick = { navigate(navController, tabItem.route) },
                icon = {
                    val iconTint = if (selected) {
                        MaterialTheme.colorScheme.background
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                    Icon(imageVector = tabItem.icon, contentDescription = null, tint = iconTint)
                },
                label = {
                    Text(
                        text = stringResource(tabItem.iconTextId),
                        fontSize = 15.sp
                    )
                }
            )
        }
    }
}

@Composable
private fun getIndicatorColor(): Color {
    val indicator = MaterialTheme.colorScheme.outline
    val indicatorRed = indicator.red + 0.09f
    val indicatorGreen = indicator.green + 0.1f
    val indicatorBlue = indicator.blue + 0.06f
    return Color(indicatorRed, indicatorGreen, indicatorBlue)
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