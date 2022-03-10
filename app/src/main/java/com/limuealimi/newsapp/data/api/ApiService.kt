package com.limuealimi.newsapp.data.api

import com.limuealimi.newsapp.data.model.ArticleDTO
import com.limuealimi.newsapp.data.model.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException

const val API_KEY = "211ef9928f694387aeed2a7f275253fe"

interface ApiService {
    @GET("everything")
    suspend fun getArticles(@Query("q") search : String = "q", @Query("apiKey") apiKey : String = API_KEY): ArticleResponse
}