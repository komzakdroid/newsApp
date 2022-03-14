package com.limuealimi.newsapp.domain.repository

import com.limuealimi.newsapp.domain.model.Article


interface MainRepository {
    suspend fun getArticles(): Result<List<Article>>
}