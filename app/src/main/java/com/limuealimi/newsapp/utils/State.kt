package com.limuealimi.newsapp.utils

sealed class State<out T> {
    object Loading : State<Nothing>()
    object Empty : State<Nothing>()
    object NoConnection : State<Nothing>()
    data class Content<out T>(val content: T) : State<T>()
    data class Error<out T>(val error: Throwable?) : State<T>()
}