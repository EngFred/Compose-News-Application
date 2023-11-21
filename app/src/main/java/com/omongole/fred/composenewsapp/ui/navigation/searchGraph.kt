package com.omongole.fred.composenewsapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.omongole.fred.composenewsapp.ui.screens.SearchScreen
import com.omongole.fred.composenewsapp.ui.viewModels.SharedViewModel

fun NavGraphBuilder.searchGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    navigation(
        startDestination = Route.SearchScreen.destination, route = Graph.SEARCH
    ) {
        composable(
            route = "${Route.SearchScreen.destination}/{query}",
            arguments = listOf(
                navArgument( name = "query" ) { type = NavType.StringType }
            )
        ) {
            val searchQuery = it.arguments?.getString("query")
            SearchScreen( searchQuery = searchQuery, showArticleDetails = { article ->
                sharedViewModel.addArticle(article)
                navController.navigate(Route.DetailScreen.destination)
            })
        }
    }
}