package com.limuealimi.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

class HomeViewModel(
    private val useCase: ArticleCardUseCase,
) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private var newPagingSource: PagingSource<*, *>? = null

    @ExperimentalCoroutinesApi
    val articles: StateFlow<PagingData<Article>> = query
        .map(::newPager)
        .flatMapLatest { pager -> pager.flow }
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


    private fun newPager(query: String): Pager<Int, Article> {
        return Pager(PagingConfig(5, enablePlaceholders = false)) {
            useCase.loadArticlesData(query).also { newPagingSource = it }
        }
    }

    fun setQuery(query: String) {
        _query.tryEmit(query)
    }
}
