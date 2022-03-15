package com.limuealimi.newsapp.fakeData

import com.limuealimi.newsapp.data.model.ArticleDTO

class EmptyInstance {
    companion object {
        fun emptyInstanceOfArticles(): ArticleDTO =
            ArticleDTO("", "", "", "", "", "", "")
    }
}