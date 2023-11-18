package com.omongole.fred.composenewsapp.domain.local

import com.omongole.fred.composenewsapp.data.local.ArticlesDao
import com.omongole.fred.composenewsapp.data.local.ArticlesRepository
import com.omongole.fred.composenewsapp.data.modal.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val dao: ArticlesDao
) : ArticlesRepository {
    override suspend fun getAllArticles(): Flow<List<Article>> {
        return dao.getArticles()
    }

    override suspend fun saveArticle(article: Article) {
        return dao.saveArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        return dao.deleteArticle(article)
    }

    override suspend fun getArticle(url: String): Article? {
        return dao.getArticle(url)
    }
}