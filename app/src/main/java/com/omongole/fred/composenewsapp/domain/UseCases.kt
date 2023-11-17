package com.omongole.fred.composenewsapp.domain

import android.util.Log
import androidx.paging.PagingData
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.data.modal.NewsApiResponse
import com.omongole.fred.composenewsapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

const val TAG = "USE_CASE"
class UseCases @Inject constructor(
    private val repository: NewsRepositoryImpl
) {
    suspend operator fun invoke( sources: List<String>) = repository.getNews( sources )
}