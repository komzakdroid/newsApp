package com.limuealimi.newsapp.utils

import com.limuealimi.newsapp.domain.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> fetchDataHelper(localData: ArrayList<T>, dataFromApi: List<T>): List<T> {
    if (localData.isNotEmpty())
        return localData
    withContext(Dispatchers.IO) {
        val items = dataFromApi
        localData.updateList(items)
    }
    return localData
}