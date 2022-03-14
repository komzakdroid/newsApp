package com.limuealimi.newsapp.domain.usecase

import com.limuealimi.newsapp.domain.model.Article

interface ArticleCardUseCase {
    suspend fun loadArticlesData(): Result<List<Article>>

}