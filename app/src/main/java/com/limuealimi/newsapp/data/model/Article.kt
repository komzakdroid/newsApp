package com.limuealimi.newsapp.data.model

data class Article(
    val source: Source?,
    val title: String?,
    val url: String?,
    val description: String?,
    val author: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)
