package com.omongole.fred.composenewsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.omongole.fred.composenewsapp.ui.screens.bookmark.BookMarkScreen
import com.omongole.fred.composenewsapp.ui.screens.home.HomeScreen
import com.omongole.fred.composenewsapp.ui.screens.search.SearchAssistedFactory
import com.omongole.fred.composenewsapp.ui.viewModels.SharedViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    assistedFactory: SearchAssistedFactory
) {
    val sharedViewModel: SharedViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Route.HomeScreen.destination,
        route = Graph.BOTTOM_NAVIGATION
    ) {
        composable( route = Route.HomeScreen.destination ) {
            HomeScreen( onSearchClicked = { searchQuery ->
                navController.navigate("${Route.SearchScreen.destination}/${searchQuery}")
            }, showArticleDetails = {
                sharedViewModel.addArticle(it)
                navController.navigate(Route.DetailScreen.destination)
            })
        }
        composable( route = Route.BookmarkScreen.destination ) {
            BookMarkScreen( sharedViewModel = sharedViewModel, navController = navController )
        }
        detailGraph(sharedViewModel = sharedViewModel, navHostController = navController)
        searchGraph(navController, sharedViewModel, assistedFactory )
    }
}

object Graph {
    const val BOTTOM_NAVIGATION = "bottom_nav_graph"
    const val DETAIL = "detail_graph"
    const val SEARCH = "search_graph"
}