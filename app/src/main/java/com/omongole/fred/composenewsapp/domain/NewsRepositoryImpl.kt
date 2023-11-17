package com.omongole.fred.composenewsapp.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.omongole.fred.composenewsapp.data.api.ApiInterface
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.data.repository.NewsRepository
import com.omongole.fred.composenewsapp.data.source.RemoteSource
import com.omongole.fred.composenewsapp.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: ApiInterface
) : NewsRepository {
    override suspend fun getNews( sources: List<String> ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig( ITEMS_PER_PAGE ),
            pagingSourceFactory = {
                RemoteSource( api, sources.joinToString(","))
            }
        ).flow
    }
}