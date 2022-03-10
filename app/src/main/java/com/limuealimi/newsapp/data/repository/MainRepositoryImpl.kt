package com.limuealimi.newsapp.data.repository

import com.limuealimi.newsapp.data.api.ApiService
import com.limuealimi.newsapp.domain.model.Article
import com.limuealimi.newsapp.domain.repository.MainRepository
import com.limuealimi.newsapp.utils.fetchDataHelper

class MainRepositoryImpl(private val apiService: ApiService) : MainRepository {

    private val localCache = ArrayList<Article>()

    override suspend fun getArticles(): List<Article> {
        return fetchDataHelper(localCache, apiService.getArticles().articles.map { it.mapToArticleDTO() })
    }
}