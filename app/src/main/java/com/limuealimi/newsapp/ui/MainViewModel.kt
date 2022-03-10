package com.limuealimi.newsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limuealimi.newsapp.domain.model.Article
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCase
import com.limuealimi.newsapp.utils.State
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

class MainViewModel(
    private val useCase: ArticleCardUseCase
) : ViewModel() {

    private val _articleData = MutableLiveData<State<List<Article>>>()
    val articleData: LiveData<State<List<Article>>> = _articleData

    init {
        _articleData.value = State.Empty
    }

    fun getArticlesData() {
        viewModelScope.launch {
            _articleData.value = State.Loading
            try {
                val articles = useCase.loadArticlesData()
                val state = if (articles.isEmpty()) State.Empty else State.Content(articles)
                _articleData.value = state
            } catch (e: Exception) {
                _articleData.value = State.Error(e.message)
            }
        }
    }
}