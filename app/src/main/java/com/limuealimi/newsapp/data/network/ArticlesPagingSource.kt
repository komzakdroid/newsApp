package com.limuealimi.newsapp.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.data.network.api.ApiService
import com.limuealimi.newsapp.utils.toArticle
import retrofit2.HttpException

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
            val pageSize = params.loadSize.coerceAtMost(ApiService.MAX_PAGE_SIZE)
            val response = apiService.everything(query, pageNumber, pageSize)

            if (response.isSuccessful) {
                val articles = response.body()!!.articles.map { it.toArticle() }
                val nextPageNumber = if (articles.isEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
                return LoadResult.Page(articles, prevPageNumber, nextPageNumber)
            } else {
                return LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}