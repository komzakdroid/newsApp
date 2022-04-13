package com.limuealimi.newsapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.data.network.ArticlesPagingSource
import com.limuealimi.newsapp.data.network.api.ApiService

class MainRepositoryImpl(
    private val apiService: ApiService
) : MainRepository {
    override suspend fun getArticles(query: String): LiveData<PagingData<Article>> {
        Log.d("repo_query", "getArticles:$query ")
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                Log.d("CHECKING_Factory", "getArticles: *********")
                ArticlesPagingSource(apiService, query)
            }
        ).liveData
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}