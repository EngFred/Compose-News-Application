package com.omongole.fred.composenewsapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.domain.remote.UseCases
import com.omongole.fred.composenewsapp.utils.Constants.SOURCES
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel()  {

    private val _searchedNews = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val searchedNews = _searchedNews.asStateFlow()

    fun searchNews(  searchQuery: String ) {
        viewModelScope.launch ( Dispatchers.IO ) {
            useCases.invoke(  searchQuery, SOURCES ).cachedIn( viewModelScope ).collectLatest {
                _searchedNews.value = it
            }
        }
        Log.d("SEARCH_SCREEN_VIEW_MODEL", "${_searchedNews.value}")
    }
}