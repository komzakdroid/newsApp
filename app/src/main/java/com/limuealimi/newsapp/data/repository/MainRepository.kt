package com.limuealimi.newsapp.data.repository

import com.limuealimi.newsapp.data.model.Article


interface MainRepository {
    suspend fun getArticles(pageNumber: Int): Result<List<Article>>
}