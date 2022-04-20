package com.limuealimi.newsapp.data.network.api

import androidx.annotation.IntRange
import com.limuealimi.newsapp.data.network.model.ArticleResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface ApiService {
    @GET("/v2/everything")
    suspend fun everything(
        @Query("q") query: String? = null,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("pageSize") @IntRange(
            from = 1,
            to = MAX_PAGE_SIZE.toLong()
        ) pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("qInTitle") queryInBody: String? = null,
        @Query("sources") sources: String? = null,
        @Query("domains") domains: String? = null,
        @Query("excludeDomains") excludeDomains: String? = null,
        @Query("from") from: Date? = null,
        @Query("to") to: Date? = null,
        @Query("language") language: Language? = null,
        @Query("sortBy") sortBy: SortBy? = null,
        @Query("apiKey") API_KEY: String = "a55a9aeb1efd47a6aebc2992e7a0d686"
    ): ArticleResponseDTO

    enum class Language {
        ar,
        de,
        en,
        es,
        fr,
        he,
        it,
        nl,
        no,
        pt,
        ru,
        se,
        ud,
        zh,
    }

    enum class SortBy {
        /**
         * articles more closely related to query come first.
         */
        relevancy,
        popularity,
        publishedAt
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
        const val MAX_PAGE_SIZE = 20
    }

}