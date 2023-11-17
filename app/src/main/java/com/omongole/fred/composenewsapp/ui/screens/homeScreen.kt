package com.omongole.fred.composenewsapp.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.data.modal.Source
import com.omongole.fred.composenewsapp.ui.components.ArticlesList
import com.omongole.fred.composenewsapp.ui.components.ErrorComposable
import com.omongole.fred.composenewsapp.ui.components.Loader
import com.omongole.fred.composenewsapp.ui.components.SearchWidget
import com.omongole.fred.composenewsapp.ui.navigation.Route
import com.omongole.fred.composenewsapp.ui.theme.ComposeNewsAppTheme
import com.omongole.fred.composenewsapp.ui.viewModel.MainViewModel
import com.omongole.fred.composenewsapp.ui.viewModel.SharedViewModel

const val TAG = "MAIN_SCREEN"

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val articles = mainViewModel.news.collectAsLazyPagingItems()

    val error = when {
        articles.loadState.refresh is LoadState.Error  -> articles.loadState.refresh as LoadState.Error
        articles.loadState.prepend is LoadState.Error  -> articles.loadState.prepend as LoadState.Error
        articles.loadState.append is LoadState.Error  -> articles.loadState.append as LoadState.Error
        else -> null
    }

    when{
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
                    text = "",
                    onTextChange = {} ,
                    onSearchClicked = {}
                ) {
                    
                }
                ArticlesList( articles = articles, onClick = {
//                    navController.currentBackStackEntry?.savedStateHandle?.set( "article", it )
                    sharedViewModel.addArticle(it)
                    navController.navigate(Route.DetailScreen.destination)
            })
        }
    }
}}

