package com.limuealimi.newsapp.data.api

import com.limuealimi.newsapp.BuildConfig
import com.limuealimi.newsapp.data.model.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getArticles(
        @Query("q")
        search: String = "q",
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY,
        @Query("page")
        page: Int,
        @Query("pageSize")
        pageSize: Int,
    ): ArticleResponse

    @GET("v2/everything")
    suspend fun getSearch(
        @Query("q")
        searchQuery: String = "SEARCH BY WORD",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): ArticleResponse
}