package com.limuealimi.newsapp.domain.usecase

import android.util.Log
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
        Log.d("usecase_query", "loadSearchedArticleData: $query")
        return withContext(dispatchers.default()) { repository.getArticles(query) }
    }
}