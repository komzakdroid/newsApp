package com.limuealimi.newsapp.data.repository

import androidx.paging.PagingSource
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.data.network.ArticlesPagingSource
import com.limuealimi.newsapp.data.network.api.ApiService

class MainRepositoryImpl(
    private val apiService: ApiService
) : MainRepository {
    override suspend fun getArticles(query: String): PagingSource<Int, Article> {
        return ArticlesPagingSource(apiService, query)
    }
}