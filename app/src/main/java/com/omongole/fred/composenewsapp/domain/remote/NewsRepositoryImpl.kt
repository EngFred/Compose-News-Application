package com.omongole.fred.composenewsapp.domain.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.omongole.fred.composenewsapp.data.remote.api.ApiInterface
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.data.remote.repository.NewsRepository
import com.omongole.fred.composenewsapp.data.remote.source.GetNewsPagingSource
import com.omongole.fred.composenewsapp.data.remote.source.SearchNewsPagingSource
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
                GetNewsPagingSource( api, sources.joinToString(","))
            }
        ).flow
    }

    override suspend fun searchNews(
        searchQuery: String,
        sources: List<String>
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig( ITEMS_PER_PAGE ),
            pagingSourceFactory = {
                SearchNewsPagingSource( api, searchQuery, sources.joinToString(",") )
            }
        ).flow
    }
}