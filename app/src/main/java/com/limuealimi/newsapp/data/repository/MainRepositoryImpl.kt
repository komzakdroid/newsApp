package com.limuealimi.newsapp.data.repository

import com.limuealimi.newsapp.data.api.ApiService
import com.limuealimi.newsapp.data.model.Article
import java.lang.Exception

class MainRepositoryImpl(
    private val apiService: ApiService
) : MainRepository {
    override suspend fun getArticles(pageNumber: Int): Result<List<Article>> {
        return try {
            Result.success(apiService.getArticles(pageNumber = pageNumber).articles.map { it.mapToArticleDTO() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}