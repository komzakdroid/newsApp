package com.limuealimi.newsapp.data.repository

import com.limuealimi.newsapp.domain.model.Article


interface MainRepository {
    suspend fun getArticles(): Result<List<Article>>
}