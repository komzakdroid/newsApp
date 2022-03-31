package com.limuealimi.newsapp.data

import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.data.model.Source
import com.limuealimi.newsapp.data.network.model.ArticleDTO
import com.limuealimi.newsapp.data.network.model.SourceDTO

internal fun ArticleDTO.toArticle(): Article {
    return Article(
        source = this.source?.toSource(),
        title = title,
        url = url,
        description = description,
        author = author,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

private fun SourceDTO.toSource(): Source {
    return Source(id = id, name = name)
}