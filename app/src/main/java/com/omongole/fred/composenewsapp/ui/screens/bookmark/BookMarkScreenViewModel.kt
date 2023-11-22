package com.omongole.fred.composenewsapp.ui.screens.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.domain.local.usecases.GetAllArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkScreenViewModel @Inject constructor(
    private val getAllArticlesUseCase: GetAllArticlesUseCase
) : ViewModel() {

    private val _savedArticles = MutableStateFlow<List<Article>>(emptyList())
    val savedArticles = _savedArticles.asStateFlow()

    init {
        getAllSavedArticles()
    }

    private fun getAllSavedArticles() {
        viewModelScope.launch( Dispatchers.IO ) {
            getAllArticlesUseCase().collectLatest {
                _savedArticles.value = it.asReversed()
            }
        }
    }
}