package com.limuealimi.newsapp.data.repository

import androidx.paging.PagingData
import com.limuealimi.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow


interface MainRepository {
    suspend fun getArticles(query: String): Flow<PagingData<Article>>
}