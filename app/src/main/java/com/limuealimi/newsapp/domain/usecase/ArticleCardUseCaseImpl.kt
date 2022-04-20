package com.limuealimi.newsapp.domain.usecase

import androidx.paging.PagingData
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.data.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ArticleCardUseCaseImpl(
    private val repository: MainRepository,
    private val dispatcher: CoroutineContext
) : ArticleCardUseCase {
    override suspend fun loadSearchedArticleData(query: String): Flow<PagingData<Article>> {
        return withContext(dispatcher) { repository.getArticles(query) }
    }
}