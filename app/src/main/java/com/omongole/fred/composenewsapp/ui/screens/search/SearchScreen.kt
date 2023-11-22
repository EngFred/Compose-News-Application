package com.omongole.fred.composenewsapp.ui.screens.search


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.ui.components.ArticlesList
import com.omongole.fred.composenewsapp.ui.components.EmptyResult
import com.omongole.fred.composenewsapp.ui.components.ErrorComposable
import com.omongole.fred.composenewsapp.ui.components.Loader

@Composable
fun SearchScreen(
    showArticleDetails: (Article) -> Unit,
    searchQuery: String,
    assistedFactory: SearchAssistedFactory
) {

    val viewModel =  viewModel(
        modelClass = SearchScreenViewModel::class.java,
        factory = SearchViewModelFactory(
            searchQuery, assistedFactory
        )
    )

    val searchedArticles = viewModel.searchedNews.collectAsLazyPagingItems()

    Column( modifier = Modifier.fillMaxSize()) {
        when (searchedArticles.loadState.refresh) {
            is LoadState.Loading -> { Loader() }
            is LoadState.Error -> {
                val error = searchedArticles.loadState.refresh as LoadState.Error
                ErrorComposable(errorMessage = "${error.error.message}!", retry = {
                    viewModel.searchNews(searchQuery)
                })
            }
            else -> {
                if ( searchedArticles.itemCount == 0 ) {
                    EmptyResult(text = "No News related to your search!")
                }else {
                    ArticlesList(articles = searchedArticles, onArticleClick = {
                        showArticleDetails(it)
                    })
                }
            }
        }
    }

}