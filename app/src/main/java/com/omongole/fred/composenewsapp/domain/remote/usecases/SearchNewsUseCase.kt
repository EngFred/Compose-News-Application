package com.omongole.fred.composenewsapp.domain.remote.usecases

import com.omongole.fred.composenewsapp.data.remote.repository.NewsRepository
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val remoteRepository: NewsRepository
) {
    suspend operator fun invoke( searchQuery: String, sources: List<String> ) = remoteRepository.searchNews( searchQuery, sources )
}