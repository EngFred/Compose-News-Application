package com.omongole.fred.composenewsapp.ui.screens.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.domain.remote.usecases.SearchNewsUseCase
import com.omongole.fred.composenewsapp.utils.Constants.SOURCES
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchScreenViewModel @AssistedInject constructor(
    private val searchNewsUseCase: SearchNewsUseCase,
    @Assisted private val searchQuery: String
) : ViewModel()  {

    private val _searchedNews = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val searchedNews = _searchedNews.asStateFlow()

    init {
        searchNews( searchQuery )
    }

    fun searchNews( searchQuery: String ) {
        viewModelScope.launch ( Dispatchers.IO ) {
            searchNewsUseCase( searchQuery, SOURCES ).cachedIn( viewModelScope ).collectLatest {
                _searchedNews.value = it
            }
        }
        Log.d("SEARCH_SCREEN_VIEW_MODEL", "${_searchedNews.value}")
    }
}
