package com.omongole.fred.composenewsapp.data.local

import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.domain.local.ArticlesRepositoryImpl
import javax.inject.Inject

class ArticlesUseCase @Inject constructor(
    private val localRepository: ArticlesRepositoryImpl
) {
    suspend fun saveArticleUseCase( article: Article ) = localRepository.saveArticle(article)
    suspend fun deleteArticleUseCase( article: Article ) = localRepository.deleteArticle(article)
    suspend fun getAllArticlesUseCase() = localRepository.getAllArticles()

    suspend fun getArticle( url: String ) = localRepository.getArticle(url)
}