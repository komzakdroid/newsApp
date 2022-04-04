package com.limuealimi.newsapp.domain.usecase

import androidx.paging.PagingSource
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.data.repository.MainRepository
import com.limuealimi.newsapp.utils.DefaultDispatcherProvider
import com.limuealimi.newsapp.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class ArticleCardUseCaseImpl(
    private val repository: MainRepository,
) : ArticleCardUseCase {
    override fun loadArticlesData(query: String): PagingSource<Int, Article> {
        return repository.getArticles(query)
    }
}