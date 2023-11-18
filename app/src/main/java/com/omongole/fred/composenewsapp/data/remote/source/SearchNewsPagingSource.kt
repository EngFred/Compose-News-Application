package com.omongole.fred.composenewsapp.data.remote.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.omongole.fred.composenewsapp.data.remote.api.ApiInterface
import com.omongole.fred.composenewsapp.data.modal.Article
import javax.inject.Inject

class SearchNewsPagingSource @Inject constructor(
    private val api: ApiInterface,
    private val searchQuery: String,
    private val sources: String
) :  PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val currentPage = params.key ?:  1
        return try {
            val searchedArticles = api.searchNews( searchQuery= searchQuery, sources = sources, page = currentPage )
            val endOfPaginationReached = searchedArticles.articles.isEmpty()
            LoadResult.Page(
                data = searchedArticles.articles.distinctBy { it.title },
                prevKey = if ( currentPage == 1 ) null else currentPage - 1,
                nextKey = if (  endOfPaginationReached ) null else currentPage + 1
            )
        } catch ( ex: Exception ) {
            Log.d("SEARCH_PAGING_SOURCE", "$ex")
            LoadResult.Error(ex)
        }
    }

}