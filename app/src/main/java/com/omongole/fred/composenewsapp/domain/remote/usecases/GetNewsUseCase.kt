package com.omongole.fred.composenewsapp.domain.remote.usecases

import com.omongole.fred.composenewsapp.data.remote.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val remoteRepository: NewsRepository
) {
    suspend operator fun invoke( sources: List<String>) = remoteRepository.getNews( sources )
}