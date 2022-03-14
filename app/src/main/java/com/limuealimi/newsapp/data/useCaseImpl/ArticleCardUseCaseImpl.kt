package com.limuealimi.newsapp.data.useCaseImpl

import com.limuealimi.newsapp.domain.model.Article
import com.limuealimi.newsapp.domain.repository.MainRepository
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCase

class ArticleCardUseCaseImpl(
    private val repository: MainRepository
) : ArticleCardUseCase {
    override suspend fun loadArticlesData(): Result<List<Article>> {
        return repository.getArticles().onSuccess { }
        //allEvents.sortedBy { it.publishedAt }
    }

}