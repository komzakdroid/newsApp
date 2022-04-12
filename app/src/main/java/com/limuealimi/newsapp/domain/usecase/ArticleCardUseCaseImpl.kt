package com.limuealimi.newsapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.data.repository.MainRepository
import com.limuealimi.newsapp.utils.DefaultDispatcherProvider
import com.limuealimi.newsapp.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class ArticleCardUseCaseImpl(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : ArticleCardUseCase {
    override suspend fun loadSearchedArticleData(query: String): LiveData<PagingData<Article>> {
        var result: LiveData<PagingData<Article>>
        withContext(dispatchers.default()) { result = repository.getArticles(query) }
        return result
    }
}