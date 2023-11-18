package com.omongole.fred.composenewsapp.data.remote.api

import com.omongole.fred.composenewsapp.data.modal.NewsApiResponse
import com.omongole.fred.composenewsapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("v2/everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ) : NewsApiResponse

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ) : NewsApiResponse

}