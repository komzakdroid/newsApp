package com.limuealimi.newsapp.data.repository

import androidx.paging.PagingSource
import com.limuealimi.newsapp.data.model.Article


interface MainRepository {
    fun getArticles(query: String): PagingSource<Int, Article>
}