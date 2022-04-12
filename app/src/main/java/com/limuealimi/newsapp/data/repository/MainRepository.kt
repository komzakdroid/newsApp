package com.limuealimi.newsapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.limuealimi.newsapp.data.model.Article


interface MainRepository {
    suspend fun getArticles(query: String): LiveData<PagingData<Article>>
}