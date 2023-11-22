package com.omongole.fred.composenewsapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.omongole.fred.composenewsapp.ui.screens.detail.DetailScreen
import com.omongole.fred.composenewsapp.ui.viewModels.SharedViewModel


fun NavGraphBuilder.detailGraph(
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController
) {
    navigation(
        route = Graph.DETAIL,
        startDestination = Route.DetailScreen.destination
    ) {
        composable( route = Route.DetailScreen.destination) {
            DetailScreen(sharedViewModel = sharedViewModel, navHostController = navHostController)
        }
    }

}