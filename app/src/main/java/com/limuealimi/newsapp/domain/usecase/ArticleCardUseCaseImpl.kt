package com.limuealimi.newsapp.domain.usecase

import com.limuealimi.newsapp.domain.model.Article
import com.limuealimi.newsapp.data.repository.MainRepository
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