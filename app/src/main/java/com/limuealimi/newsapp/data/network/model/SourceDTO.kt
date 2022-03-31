package com.limuealimi.newsapp.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourceDTO(

    @Json(name = "id") val id: String?,
    @Json(name = "name") val name: String
)