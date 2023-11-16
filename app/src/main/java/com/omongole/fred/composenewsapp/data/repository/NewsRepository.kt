package com.omongole.fred.composenewsapp.data.repository

import com.omongole.fred.composenewsapp.data.modal.NewsApiResponse
import retrofit2.Response

interface NewsRepository {
    suspend fun getNewsHeadlines(): Response<NewsApiResponse>
}