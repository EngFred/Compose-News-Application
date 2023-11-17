package com.omongole.fred.composenewsapp.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.omongole.fred.composenewsapp.data.api.ApiInterface
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.utils.Constants.ITEMS_PER_PAGE
import javax.inject.Inject

class RemoteSource @Inject constructor (
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
                prevKey = if ( currentPage == 1) null else currentPage - 1,
                nextKey = if ( endOfPaginationReached ) null else currentPage + 1
            )

        } catch ( ex: Exception ) {
            LoadResult.Error(ex)
        }
    }
}