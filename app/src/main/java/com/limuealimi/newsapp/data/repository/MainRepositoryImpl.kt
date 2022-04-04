package com.limuealimi.newsapp.data.repository

import androidx.paging.PagingSource
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.data.network.ArticlesPagingSource

class MainRepositoryImpl(
    private val articlesPagingSource: ArticlesPagingSource.Factory
) : MainRepository {
    override fun getArticles(query: String): PagingSource<Int, Article> {
        return articlesPagingSource.create(query)
    }
}