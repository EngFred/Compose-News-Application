package com.omongole.fred.composenewsapp.ui.screens.home

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

const val TAG = "HOME_SCREEN"

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
    onSearchClicked: (String?) -> Unit,
    showArticleDetails: (Article) -> Unit
) {
    val articles = homeScreenViewModel.news.collectAsLazyPagingItems()

    Column( modifier = Modifier.fillMaxSize()) {
        when (articles.loadState.refresh) {
            is LoadState.Loading -> { Loader() }
            is LoadState.Error -> {
                val error = articles.loadState.refresh as LoadState.Error
                ErrorComposable(errorMessage = "${error.error.message}!", retry = {
                    homeScreenViewModel.getNews()
                })
            }
            else -> {
                SearchWidget(
                    onTextChange = {
                        homeScreenViewModel.onEvent(HomeScreenEvent.SearchQueryChanged(it))
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
