package com.limuealimi.newsapp.data.network.model

import androidx.annotation.IntRange
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleResponseDTO(
    @Json(name = "status") val status: String,
    @Json(name = "totalResults") @IntRange(from = 1) val totalResults: Int,
    @Json(name = "message") val message: String? = null,
    @Json(name = "articles") val articles: List<ArticleDTO>
)