package com.omongole.fred.composenewsapp.domain.local.usecases

import com.omongole.fred.composenewsapp.data.local.ArticlesRepository
import com.omongole.fred.composenewsapp.data.modal.Article
import javax.inject.Inject

class SaveArticleUseCase @Inject constructor(
    private val localRepository: ArticlesRepository
) {
    suspend operator fun invoke( article: Article ) = localRepository.saveArticle(article)
}