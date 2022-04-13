package com.limuealimi.newsapp.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: ArticleCardUseCase
) : ViewModel() {
    private val _articleList = MutableLiveData<PagingData<Article>>()
    val articleList: LiveData<PagingData<Article>>
        get() = _articleList
    private val _query = MutableLiveData<String>()
    val query: LiveData<String>
        get() = _query

    init {
        viewModelScope.launch {
            if (_query.value == null) {
                _query.value = "q"
            }
            Log.d("CHECK_QUERY", "queryLivedata: ${query.value}")
            _articleList.value =
                useCase.loadSearchedArticleData(query.value!!).value
        }
    }

    fun updateQuery(newQuery: String) {
        viewModelScope.launch {
            _query.value = newQuery
            _articleList.value = useCase.loadSearchedArticleData(query.value!!).value
        }
    }
}
