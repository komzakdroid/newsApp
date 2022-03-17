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
    private val useCase: ArticleCardUseCase
) : ViewModel() {

    private val _articleDataState = MutableLiveData<State<Result<List<Article>>>>(State.Loading)
    val articleDataState: LiveData<State<Result<List<Article>>>> = _articleDataState

    init {
        viewModelScope.launch {
            val articles = useCase.loadArticlesData()
            _articleDataState.value = when {
                articles.isFailure -> State.Error(articles.exceptionOrNull())
                articles.getOrNull()?.isEmpty() == true -> State.Empty
                else -> State.Content(articles)
            }
        }
    }

}