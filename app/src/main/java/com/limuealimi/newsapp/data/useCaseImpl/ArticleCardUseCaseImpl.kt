package com.limuealimi.newsapp.data.useCaseImpl

import com.limuealimi.newsapp.domain.model.Article
import com.limuealimi.newsapp.domain.repository.MainRepository
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCase
import java.util.*

class ArticleCardUseCaseImpl(
    private val repository: MainRepository
) : ArticleCardUseCase {
    override suspend fun loadArticlesData(): List<Article> {
        val allEvents = repository.getArticles()
        return (allEvents.sortedBy { it.publishedAt })
    }
}