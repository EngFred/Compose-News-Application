package com.omongole.fred.composenewsapp.data.local

import com.omongole.fred.composenewsapp.data.modal.Article
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    suspend fun getAllArticles() : Flow<List<Article>>
    suspend fun saveArticle( article: Article )

    suspend fun deleteArticle( article: Article )

    suspend fun getArticle( url: String ) : Article?
}