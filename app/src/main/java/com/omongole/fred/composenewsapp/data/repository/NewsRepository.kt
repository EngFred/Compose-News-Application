package com.omongole.fred.composenewsapp.data.repository

import androidx.paging.PagingData
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.data.modal.NewsApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsRepository {
    suspend fun getNews( sources: List<String>): Flow<PagingData<Article>>
}