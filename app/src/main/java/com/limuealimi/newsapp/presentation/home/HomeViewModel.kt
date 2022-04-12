package com.limuealimi.newsapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCase

class HomeViewModel(
    private val useCase: ArticleCardUseCase
) : ViewModel() {
    private val _articleList = MutableLiveData<PagingData<Article>>()
    private val query = MutableLiveData<String>()

    fun updateQuery(newQuery: String) {
        query.value = newQuery
    }

    suspend fun getArticles(): LiveData<PagingData<Article>> {
        if (query.value == null) {
            query.value = "q"
        }
        val result = useCase.loadSearchedArticleData(query.value!!).cachedIn(viewModelScope)
        _articleList.value = result.value
        return result
    }
}
