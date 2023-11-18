package com.omongole.fred.composenewsapp.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.omongole.fred.composenewsapp.data.modal.Article


class SharedViewModel : ViewModel() {
    var article by mutableStateOf<Article?>(null)
        private set

    fun addArticle( newsArticle: Article ) {
        article = newsArticle
    }
}