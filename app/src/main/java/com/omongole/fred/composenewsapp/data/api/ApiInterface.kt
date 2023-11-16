package com.omongole.fred.composenewsapp.data.api

import com.omongole.fred.composenewsapp.data.modal.NewsApiResponse
import com.omongole.fred.composenewsapp.utils.Constants.API_KEY
import com.omongole.fred.composenewsapp.utils.Constants.COUNTRY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadlines(
        @Query("country") country: String = COUNTRY,
        @Query("apiKey") apiKey: String = API_KEY
    ) : Response<NewsApiResponse>

}