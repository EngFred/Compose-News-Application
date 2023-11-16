package com.omongole.fred.composenewsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.omongole.fred.composenewsapp.ui.screens.HomeScreen

@Composable
fun MobileNavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController  = navController  , startDestination = Route.HomeScreen.destination ) {
        composable( route = Route.HomeScreen.destination ) {
            HomeScreen()
        }
    }
}