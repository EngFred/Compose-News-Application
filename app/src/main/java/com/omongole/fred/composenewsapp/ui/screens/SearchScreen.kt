package com.omongole.fred.composenewsapp.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.ui.components.ArticlesList
import com.omongole.fred.composenewsapp.ui.viewModels.SearchScreenViewModel

@Composable
fun SearchScreen(
    searchScreenViewModel: SearchScreenViewModel = hiltViewModel(),
    searchQuery: String?,
    showArticleDetails: (Article) -> Unit
) {
    val searchedArticles = searchScreenViewModel.searchedNews.collectAsLazyPagingItems()

    searchScreenViewModel.searchNews(searchQuery!!)
    Log.d("SEARCH_SCREEN", "$searchQuery")

    ArticlesList(articles = searchedArticles, onArticleClick = {
        showArticleDetails(it)
    })
}