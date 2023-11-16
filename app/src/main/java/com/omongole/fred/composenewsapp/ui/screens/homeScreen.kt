package com.omongole.fred.composenewsapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.omongole.fred.composenewsapp.data.modal.NewsApiResponse
import com.omongole.fred.composenewsapp.ui.components.ErrorComposable
import com.omongole.fred.composenewsapp.ui.components.Loader
import com.omongole.fred.composenewsapp.ui.components.PagerArticleComponent
import com.omongole.fred.composenewsapp.ui.viewModel.MainViewModel
import com.omongole.fred.composenewsapp.utils.Resource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val newsResponse by mainViewModel.news.collectAsState()
    
    val pagerSate = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        // provide pageCount
        35
    }

    VerticalPager(
        state = pagerSate,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 10.dp
    ) {

        when(newsResponse) {
            is Resource.Loading -> {
                Loader()
            }
            is Resource.Success -> {
                val newsArticles = (newsResponse as Resource.Success<NewsApiResponse?>).data?.articles //data =  response body from the useCase class
                PagerArticleComponent(it, newsArticles!![it])
            }
            is Resource.Failure -> {
                var error = (newsResponse as Resource.Failure).errorMessage
                ErrorComposable()
            }
        }

    }
}

