package com.limuealimi.newsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "source") val source: Source?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "urlToImage") val urlToImage: String?,
    @ColumnInfo(name = "publishedAt") val publishedAt: String?,
    @ColumnInfo(name = "source") val content: String?
)
