package com.limuealimi.newsapp.data.paging.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.limuealimi.newsapp.data.api.ApiService
import com.limuealimi.newsapp.data.model.ArticleDTO
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class ArticlePagingDataSource(
    private val service: ApiService,
    private val query: String
) :
    PagingSource<Int, ArticleDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDTO> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val apiQuery = query + IN_QUALIFIER
        return try {
            val response =
                service.getArticles(apiQuery, page = position, pageSize = params.loadSize)
            val repos = response.articles
            val nextKey = if (repos.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = repos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, ArticleDTO>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}