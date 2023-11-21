package com.omongole.fred.composenewsapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.ui.components.ArticlesList
import com.omongole.fred.composenewsapp.ui.components.ErrorComposable
import com.omongole.fred.composenewsapp.ui.components.Loader
import com.omongole.fred.composenewsapp.ui.components.SearchWidget
import com.omongole.fred.composenewsapp.ui.viewModels.HomeScreenEvent
import com.omongole.fred.composenewsapp.ui.viewModels.MainViewModel

const val TAG = "HOME_SCREEN"

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    onSearchClicked: (String?) -> Unit,
    showArticleDetails: (Article) -> Unit
) {
    val articles = mainViewModel.news.collectAsLazyPagingItems()

    Column( modifier = Modifier.fillMaxSize()) {
        when (articles.loadState.refresh) {
            is LoadState.Loading -> { Loader() }
            is LoadState.Error -> {
                val error = articles.loadState.refresh as LoadState.Error
                ErrorComposable(errorMessage = "${error.error.message}!", retry = {
                    mainViewModel.getNews()
                })
            }
            else -> {
                SearchWidget(
                    onTextChange = {
                        mainViewModel.onEvent(HomeScreenEvent.SearchQueryChanged(it))
                    },
                    onSearchClicked = {
                        onSearchClicked(it)
                    },
                    onCloseClicked = {}
                )
                ArticlesList(articles = articles, onArticleClick = { showArticleDetails(it) })
            }
        }
    }
}
