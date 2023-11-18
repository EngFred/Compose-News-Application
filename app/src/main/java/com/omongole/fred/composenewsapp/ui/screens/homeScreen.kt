package com.omongole.fred.composenewsapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.omongole.fred.composenewsapp.ui.components.ArticlesList
import com.omongole.fred.composenewsapp.ui.components.ErrorComposable
import com.omongole.fred.composenewsapp.ui.components.Loader
import com.omongole.fred.composenewsapp.ui.components.SearchWidget
import com.omongole.fred.composenewsapp.ui.navigation.Route
import com.omongole.fred.composenewsapp.ui.viewModels.HomeScreenEvent
import com.omongole.fred.composenewsapp.ui.viewModels.MainViewModel
import com.omongole.fred.composenewsapp.ui.viewModels.SharedViewModel

const val TAG = "HOME_SCREEN"

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel,
    onSearchClicked: (String?) -> Unit,
    navController: NavHostController,
) {
    val articles = mainViewModel.news.collectAsLazyPagingItems()

    val error = when {
        articles.loadState.refresh is LoadState.Error -> articles.loadState.refresh as LoadState.Error
        articles.loadState.prepend is LoadState.Error -> articles.loadState.prepend as LoadState.Error
        articles.loadState.append is LoadState.Error -> articles.loadState.append as LoadState.Error
        else -> null
    }

    when {
        articles.loadState.refresh is LoadState.Loading -> {
            Log.d(TAG, "News articles loading...")
            Loader()
        }

        error != null -> {
            Log.d(TAG, "An error occurred!")
            ErrorComposable()
        }

        else -> {
            Log.d(TAG, "News articles fetched successfully!")
            Column {
                SearchWidget(
                    onTextChange = {
                        mainViewModel.onEvent(HomeScreenEvent.SearchQueryChanged(it))
                    },
                    onSearchClicked = {
                        onSearchClicked(it)
                    }
                ) {

                }
                ArticlesList(articles = articles, onClick = {
                    sharedViewModel.addArticle(it)
                    navController.navigate(Route.DetailScreen.destination)
                })
            }
        }
    }
}
