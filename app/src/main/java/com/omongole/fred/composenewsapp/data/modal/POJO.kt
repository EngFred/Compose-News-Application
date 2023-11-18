package com.omongole.fred.composenewsapp.data.modal

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

data class NewsApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
@Parcelize
@Entity
data class Article(
    val source: @RawValue Source?,
    val author: String?,
    @PrimaryKey
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val url: String?,
    val publishedAt: String?
) : Parcelable

data class Source(
    val name: String?
)