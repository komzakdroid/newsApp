package com.limuealimi.newsapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCase
import com.limuealimi.newsapp.utils.State
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: ArticleCardUseCase,
) : ViewModel() {
    private val pageNumber: LiveData<Int> = MutableLiveData()
    private val _articleDataState = MutableLiveData<State<Result<List<Article>>>>(State.Loading)
    val articleDataState: LiveData<State<Result<List<Article>>>> = _articleDataState
    private var searchedDataList = listOf<Article>()

    init {
        pageNumber.value?.let { getArticlesByPage(pageNumber = it) }
    }

    fun getArticlesByPage(pageNumber: Int) {
        viewModelScope.launch {
            val articles = useCase.loadArticlesData(pageNumber)
            when {
                articles.isFailure -> {
                    _articleDataState.value = State.Error(articles.exceptionOrNull())
                }
                articles.getOrNull()?.isEmpty() == true -> {
                    _articleDataState.value = State.Empty
                }
                else -> {
                    _articleDataState.value = State.Content(articles)
                    searchedDataList = articles.getOrThrow()
                }
            }
        }
    }
}