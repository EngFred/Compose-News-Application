package com.omongole.fred.composenewsapp.ui.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.domain.remote.usecases.GetNewsUseCase
import com.omongole.fred.composenewsapp.utils.Constants.SOURCES
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
): ViewModel() {

    private val _news : MutableStateFlow<PagingData<Article>> = MutableStateFlow(PagingData.empty())
    val news = _news.asStateFlow()

    val uiState = mutableStateOf(HomeScreenState())

    init {
        getNews()
    }

    fun onEvent( event: HomeScreenEvent) {
        when( event ) {
            is HomeScreenEvent.SearchQueryChanged -> {
                uiState.value = uiState.value.copy(
                    searchQuery = event.searchQueryEntry
                )
                Log.d("MAIN_SCREEN_EVENT", uiState.value.toString())
            }
        }
    }

    fun getNews() {
        viewModelScope.launch( Dispatchers.IO ) {
            getNewsUseCase( SOURCES ).cachedIn( viewModelScope ).collectLatest {
                _news.value = it
            }
        }
    }
}

sealed class HomeScreenEvent {
    data class SearchQueryChanged( val searchQueryEntry: String ) : HomeScreenEvent()
}

data class HomeScreenState(
    val searchQuery: String = ""
)