package com.limuealimi.newsapp.data.repository

import com.limuealimi.newsapp.data.api.ApiService
import com.limuealimi.newsapp.domain.model.Article
import com.limuealimi.newsapp.domain.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainRepositoryImpl(
    private val apiService: ApiService,
) : MainRepository {
    override suspend fun getArticles(): Result<List<Article>> {
        return try {
            Result.success(apiService.getArticles().articles.map { it.mapToArticleDTO() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}