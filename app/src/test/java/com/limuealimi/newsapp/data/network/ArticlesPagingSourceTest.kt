package com.limuealimi.newsapp.data.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.limuealimi.newsapp.MainCoroutineRule
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.data.network.api.ApiService
import com.limuealimi.newsapp.data.network.model.ArticleResponseDTO
import com.limuealimi.newsapp.utils.toArticle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.given
import retrofit2.Response

@ExperimentalCoroutinesApi
class ArticlesPagingSourceTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = MainCoroutineRule()

    @Mock
    lateinit var api: ApiService
    lateinit var articlesPagingSource: ArticlesPagingSource

    companion object {
        private val fixture = kotlinFixture {
            nullabilityStrategy(NeverNullStrategy)

        }
        val articlesResponse = ArticleResponseDTO(
            "200",
            100,
            "",
            fixture()
        )
    }

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        articlesPagingSource = ArticlesPagingSource(api, "Android")
    }


    @Test
    fun `articles paging source load = failure - received null`() = runTest {
        given(api.everything()).willReturn(null)

        val expectedResult = PagingSource.LoadResult.Error<Int, Article>(NullPointerException())

        assertEquals(
            expectedResult.toString(), articlesPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            ).toString()
        )
    }

    @Test
    fun `articles paging source refresh = success`() = runTest {
        given(api.everything()).willReturn(Response.success(articlesResponse))

        val expectedResult = PagingSource.LoadResult.Page(
            data = articlesResponse.articles.map { it.toArticle() },
            prevKey = null,
            nextKey = 1
        )

        assertEquals(
            expectedResult, articlesPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `reviews paging source append = success`() = runTest {
        given(api.everything()).willReturn(Response.success(articlesResponse))
        val expectedResult = PagingSource.LoadResult.Page(
            data = articlesResponse.articles.map { it.toArticle() },
            prevKey = 1,
            nextKey = 2
        )
        assertEquals(
            expectedResult, articlesPagingSource.load(
                PagingSource.LoadParams.Append(
                    key = 1,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `articles paging source prepend = success`() = runTest {
        given(api.everything()).willReturn(Response.success(articlesResponse))
        val expectedResult = PagingSource.LoadResult.Page(
            data = articlesResponse.articles.map { it.toArticle() },
            prevKey = null,
            nextKey = 1
        )
        assertEquals(
            expectedResult, articlesPagingSource.load(
                PagingSource.LoadParams.Prepend(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }
}