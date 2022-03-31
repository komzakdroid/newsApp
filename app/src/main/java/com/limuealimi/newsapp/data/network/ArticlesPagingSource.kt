package com.limuealimi.newsapp.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.data.network.api.ApiService

class ArticlesPagingSource constructor(
    private val apiService: ApiService,
    private val query: String
) : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        if (query.isBlank()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }

        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize = params.loadSize.coerceAtMost(apiService)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        TODO("Not yet implemented")
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}