package com.omongole.fred.composenewsapp.data.source

import com.omongole.fred.composenewsapp.data.api.ApiInterface
import javax.inject.Inject

class RemoteSource @Inject constructor (
    private val api: ApiInterface
) {
    suspend fun getNewsHeadlines() = api.getNewsHeadlines()
}