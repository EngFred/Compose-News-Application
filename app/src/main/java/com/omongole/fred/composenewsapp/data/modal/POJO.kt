package com.omongole.fred.composenewsapp.data.modal

data class NewsApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String?
)

data class Source(
    val name: String?
)