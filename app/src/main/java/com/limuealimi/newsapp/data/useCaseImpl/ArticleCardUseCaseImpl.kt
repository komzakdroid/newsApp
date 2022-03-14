package com.limuealimi.newsapp.data.useCaseImpl

import com.limuealimi.newsapp.domain.model.Article
import com.limuealimi.newsapp.domain.repository.MainRepository
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ArticleCardUseCaseImpl(
    private val repository: MainRepository,
    private val dispatcher: CoroutineDispatcher
) : ArticleCardUseCase {
    override suspend fun loadArticlesData(): Result<List<Article>> {
        return withContext(dispatcher) {
            repository.getArticles()
        }
    }
}