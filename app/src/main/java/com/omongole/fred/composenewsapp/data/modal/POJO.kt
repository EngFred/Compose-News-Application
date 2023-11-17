package com.omongole.fred.composenewsapp.data.modal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

data class NewsApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
@Parcelize
data class Article(
    val source: @RawValue Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String?
) : Parcelable

data class Source(
    val name: String?
)