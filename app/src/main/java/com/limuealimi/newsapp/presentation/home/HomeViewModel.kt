package com.limuealimi.newsapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limuealimi.newsapp.domain.model.Article
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCase
import com.limuealimi.newsapp.utils.State
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: ArticleCardUseCase
) : ViewModel() {

    private val _articleData = MutableLiveData<State<Result<List<Article>>>>()
    val articleData: LiveData<State<Result<List<Article>>>> = _articleData

    init {
        _articleData.value = State.Empty
    }

    fun getArticlesData() {
        viewModelScope.launch {
            _articleData.value = State.Loading
            val articles = useCase.loadArticlesData()
            _articleData.value =
                when {
                    articles.isFailure -> State.Error(articles.exceptionOrNull())
                    articles.getOrNull()?.isEmpty() == true -> State.Empty
                    else ->
                        State.Content(articles)

                    /* try {
                         val articles = useCase.loadArticlesData()
                         val state =
                             if (articles.isFailure) State.Empty else State.Content(articles)
                         _articleData.value = state
                     } catch (e: Exception) {
                         _articleData.value = State.Error(e.message)
                     }*/
                }
        }
    }
}