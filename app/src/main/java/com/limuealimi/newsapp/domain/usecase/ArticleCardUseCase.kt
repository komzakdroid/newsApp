package com.limuealimi.newsapp.domain.usecase

import androidx.paging.PagingSource
import com.limuealimi.newsapp.data.model.Article

interface ArticleCardUseCase {
    fun loadArticlesData(
        query: String
    ): PagingSource<Int, Article>
}