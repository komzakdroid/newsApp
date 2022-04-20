package com.limuealimi.newsapp.presentation.home

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: ArticleCardUseCase
) : ViewModel() {
    private val _query = MutableLiveData<String>("q")

    val articleList = MediatorLiveData<PagingData<Article>>().apply {
        addSource(_query) { query ->
            val q = if (query.isNullOrEmpty()) "q" else query
            viewModelScope.launch {
                useCase.loadSearchedArticleData(q).onEach {
                    value = it
                }.launchIn(this)
            }
        }
    }

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }
}
