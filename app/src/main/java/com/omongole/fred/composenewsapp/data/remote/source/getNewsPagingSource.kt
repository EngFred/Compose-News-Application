package com.omongole.fred.composenewsapp.data.remote.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.omongole.fred.composenewsapp.data.remote.api.ApiInterface
import com.omongole.fred.composenewsapp.data.modal.Article
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class GetNewsPagingSource @Inject constructor (
    private val api: ApiInterface,
    private val sources: String
): PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val currentPage = params.key ?: 1
            val apiResponse = api.getNews( page = currentPage, sources = sources)
            val endOfPaginationReached = apiResponse.articles.isEmpty()

            LoadResult.Page(
                data = apiResponse.articles.distinctBy { it.title } ,
                prevKey = if ( currentPage == 1 ) null else currentPage - 1,
                nextKey = if ( endOfPaginationReached ) null else currentPage + 1
            )

        } catch ( ex: Exception ) {
            val error: String
            when( ex ) {
                is UnknownHostException -> {
                    error = "No Internet Connection"
                }
                is SocketTimeoutException -> {
                    error = "Request Timed out! Your internet connection could be slow."
                }
                else -> {
                    error = ex.toString()
                }
            }
            LoadResult.Error(Throwable(error))
        }
    }
}