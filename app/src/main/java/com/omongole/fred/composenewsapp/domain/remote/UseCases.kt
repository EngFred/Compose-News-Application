package com.omongole.fred.composenewsapp.domain.remote


import javax.inject.Inject

class UseCases @Inject constructor(
    private val repository: NewsRepositoryImpl
) {
    suspend operator fun invoke( sources: List<String>) = repository.getNews( sources )
    suspend operator fun invoke( searchQuery: String, sources: List<String> ) = repository.searchNews( searchQuery, sources )
}