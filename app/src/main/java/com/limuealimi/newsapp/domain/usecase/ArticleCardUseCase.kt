package com.limuealimi.newsapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.limuealimi.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleCardUseCase {
    suspend fun loadSearchedArticleData(
        query: String
    ): Flow<PagingData<Article>>
}