package com.omongole.fred.composenewsapp.domain

import android.util.Log
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
    fun getNewsHeadlineUseCase(): Flow<Resource<NewsApiResponse?>> {
        return flow {
            emit(Resource.Loading)
            Log.d(TAG, "Loading news...")
            val response = repository.getNewsHeadlines()
            if (response.body() != null && response.isSuccessful) {
                emit(Resource.Success(response.body()))
                Log.d(TAG, "News headlines fetched successfully!")
            } else {
                emit(Resource.Failure("Error fetching news data!"))
                Log.d(TAG, "Response Error: ${response.errorBody()}")
            }
        }.catch {
            emit(Resource.Failure(it.localizedMessage!!))
            Log.d(TAG, "Flow Error: ${it.localizedMessage}")
        }
    }
}