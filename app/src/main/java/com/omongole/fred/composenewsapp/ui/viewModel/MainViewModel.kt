package com.omongole.fred.composenewsapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omongole.fred.composenewsapp.data.modal.NewsApiResponse
import com.omongole.fred.composenewsapp.domain.UseCases
import com.omongole.fred.composenewsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _news : MutableStateFlow<Resource<NewsApiResponse?>> = MutableStateFlow(Resource.Loading)
    val news = _news.asStateFlow()

    init {
        getNewsHeadlines()
    }

    private fun getNewsHeadlines() {
        viewModelScope.launch( Dispatchers.IO ) {
            useCases.getNewsHeadlineUseCase().collectLatest {
                _news.value = it
            }
        }
    }
}