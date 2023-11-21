package com.omongole.fred.composenewsapp.ui.components.bottomNavigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.omongole.fred.composenewsapp.ui.navigation.Route


@Composable
fun BottomBar(
    navController: NavHostController
) {

    val screens = listOf(
        NavigationBarItem(
            Route.HomeScreen,
            "Home",
            Icons.Default.Home
        ),
        NavigationBarItem(
            Route.BookmarkScreen,
            "Bookmarks",
            Icons.Default.Star
        )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestinations = screens.any { it.screen.destination == currentDestination?.route }
    if ( bottomBarDestinations ) {
        NavigationBar(
            Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.background,
            tonalElevation = 10.dp
        ) {
            screens.forEach {
                AddItem(
                    screen = it.screen,
                    currentDestination = currentDestination ,
                    navController = navController,
                    title = it.title,
                    icon = it.icon
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Route,
    currentDestination: NavDestination?,
    navController: NavHostController,
    title: String,
    icon: ImageVector
) {
    NavigationBarItem(
        selected = currentDestination?.hierarchy?.any{
              it.route == screen.destination
        } == true ,
        onClick = {
            navController.navigate(screen.destination){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        icon = { Icon(imageVector = icon, contentDescription = "") },
        label = { Text(text = title) }
    )
}