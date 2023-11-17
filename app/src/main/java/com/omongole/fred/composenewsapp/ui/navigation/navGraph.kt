package com.omongole.fred.composenewsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.ui.screens.DetailScreen
import com.omongole.fred.composenewsapp.ui.screens.HomeScreen
import com.omongole.fred.composenewsapp.ui.viewModel.SharedViewModel

@Composable
fun MobileNavigationGraph() {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(navController  = navController  , startDestination = Route.HomeScreen.destination ) {
        composable( route = Route.HomeScreen.destination ) {
            HomeScreen( navController = navController, sharedViewModel = sharedViewModel )
        }
        composable(
            route = Route.DetailScreen.destination)
        {
//            val article = navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
            DetailScreen(sharedViewModel)
        }
    }
}