package com.limuealimi.newsapp.data.network.model


import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.data.model.Source
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleDTO(
    @Json(name = "source") val source: SourceDTO? = null,
    @Json(name = "title") val title: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "author") val author: String?,
    @Json(name = "urlToImage") val urlToImage: String?,
    @Json(name = "publishedAt") val publishedAt: String?,
    @Json(name = "content") val content: String?,
)