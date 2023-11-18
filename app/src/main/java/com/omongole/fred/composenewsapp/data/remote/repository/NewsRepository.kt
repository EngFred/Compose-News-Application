package com.omongole.fred.composenewsapp.data.remote.repository

import androidx.paging.PagingData
import com.omongole.fred.composenewsapp.data.modal.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews( sources: List<String>): Flow<PagingData<Article>>
    suspend fun searchNews( searchQuery: String, sources: List<String> ) : Flow<PagingData<Article>>
}