package com.omongole.fred.composenewsapp.domain

import com.omongole.fred.composenewsapp.data.repository.NewsRepository
import com.omongole.fred.composenewsapp.data.source.RemoteSource
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val source: RemoteSource
) : NewsRepository {
    override suspend fun getNewsHeadlines()  = source.getNewsHeadlines()
}