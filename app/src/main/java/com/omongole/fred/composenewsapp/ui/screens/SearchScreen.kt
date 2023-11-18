package com.omongole.fred.composenewsapp.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.omongole.fred.composenewsapp.ui.components.ArticlesList
import com.omongole.fred.composenewsapp.ui.navigation.Route
import com.omongole.fred.composenewsapp.ui.viewModels.SearchScreenViewModel
import com.omongole.fred.composenewsapp.ui.viewModels.SharedViewModel

@Composable
fun SearchScreen(
    searchScreenViewModel: SearchScreenViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel,
    navController: NavHostController,
    searchQuery: String?
) {
    val searchedArticles = searchScreenViewModel.searchedNews.collectAsLazyPagingItems()

    searchScreenViewModel.searchNews(searchQuery!!)
    Log.d("SEARCH_SCREEN", "$searchQuery")

    ArticlesList(articles = searchedArticles, onClick = {
        sharedViewModel.addArticle(it)
        navController.navigate(Route.DetailScreen.destination)
    })
}