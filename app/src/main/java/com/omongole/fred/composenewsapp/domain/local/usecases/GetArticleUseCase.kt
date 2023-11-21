package com.omongole.fred.composenewsapp.domain.local.usecases

import com.omongole.fred.composenewsapp.data.local.ArticlesRepository
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
    private val localRepository: ArticlesRepository
) {
    suspend  operator fun invoke( url: String ) = localRepository.getArticle(url)
}