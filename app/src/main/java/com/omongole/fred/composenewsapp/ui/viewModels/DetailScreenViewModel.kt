package com.omongole.fred.composenewsapp.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omongole.fred.composenewsapp.data.local.ArticlesUseCase
import com.omongole.fred.composenewsapp.data.modal.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val localUseCase: ArticlesUseCase
):  ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    val articleAlreadySaved = mutableStateOf(false)

    fun onEvent( event: DetailScreenEvent ) {
        when( event ) {
            is DetailScreenEvent.SaveOrDeleteArticle -> {
                viewModelScope.launch ( Dispatchers.IO ) {
                    val article = localUseCase.getArticle( event.article.url!! )
                    if ( article == null ) {
                        viewModelScope.launch ( Dispatchers.IO ) {
                            localUseCase.saveArticleUseCase( event.article)
                            sideEffect = "Article saved!"
                        }
                        articleAlreadySaved.value = false
                    }else {
                        viewModelScope.launch ( Dispatchers.IO ) {
                            localUseCase.deleteArticleUseCase( article )
                            sideEffect = "Article removed!"
                        }
                        articleAlreadySaved.value = true
                    }
                }
            }
            is DetailScreenEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }
}

sealed class DetailScreenEvent {
    data class SaveOrDeleteArticle( val article: Article ) : DetailScreenEvent()
    object RemoveSideEffect : DetailScreenEvent()
}
