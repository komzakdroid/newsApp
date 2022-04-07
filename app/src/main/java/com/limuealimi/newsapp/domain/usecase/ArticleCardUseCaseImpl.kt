package com.limuealimi.newsapp.domain.usecase

import androidx.paging.PagingSource
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.data.repository.MainRepository
import com.limuealimi.newsapp.utils.DefaultDispatcherProvider
import com.limuealimi.newsapp.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class ArticleCardUseCaseImpl(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : ArticleCardUseCase {
    override suspend fun loadArticlesData(query: String): PagingSource<Int, Article> {
        return withContext(dispatchers.default()) {
            repository.getArticles(query)
        }
    }

}