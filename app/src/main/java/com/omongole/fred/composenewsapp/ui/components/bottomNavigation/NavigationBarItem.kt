package com.omongole.fred.composenewsapp.ui.components.bottomNavigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.omongole.fred.composenewsapp.ui.navigation.Route

data class NavigationBarItem(
    val screen: Route,
    val title: String,
    val icon: ImageVector
)
