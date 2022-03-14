package com.limuealimi.newsapp.data.api

import com.limuealimi.newsapp.BuildConfig
import com.limuealimi.newsapp.data.model.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun getArticles(
        @Query("q") search: String = "q",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): ArticleResponse
}