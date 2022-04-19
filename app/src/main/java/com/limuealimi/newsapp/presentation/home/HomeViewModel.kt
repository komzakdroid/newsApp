package com.limuealimi.newsapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCase
import kotlinx.coroutines.flow.collectLatest
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

//    private val query = MediatorLiveData<String>().apply {
//        addSource(
//            query
//        ){
//
//        }
//    }
//

    init {
        viewModelScope.launch {
            if (_query.value == null) {
                _query.value = "q"
            }
            useCase.loadSearchedArticleData(query.value!!).collectLatest {
                _articleList.value = it
            }
        }
    }

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }
}
