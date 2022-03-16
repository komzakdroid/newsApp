package com.limuealimi.newsapp.domain.usecase

import com.limuealimi.newsapp.data.model.Article

interface ArticleCardUseCase {
    suspend fun loadArticlesData(): Result<List<Article>>
}