package com.limuealimi.newsapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.limuealimi.newsapp.MainCoroutineRule
import com.limuealimi.newsapp.data.network.api.ApiService
import com.limuealimi.newsapp.data.network.model.ArticleResponse
import com.limuealimi.newsapp.wheneverBlocking
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
class MainRepositoryImplTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var subject: MainRepositoryImpl

    @Mock
    private lateinit var apiService: ApiService

    private val testDispatcher = TestCoroutineDispatcher()

    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getArticles in api service getting called one time`() {
        runBlocking {
            wheneverBlocking { apiService.getArticles() }.thenReturn(fixture())
            subject.getArticles().getOrThrow()
            verify(apiService, times(1)).getArticles()
        }
    }

    @Test
    fun `getArticles passes correct data`() {
        runBlocking {
            val articleResponse: ArticleResponse = fixture()
            wheneverBlocking { apiService.getArticles() }.thenReturn(articleResponse)
            val articleEvents = subject.getArticles().getOrThrow()
            articleEvents.forEachIndexed { i, article ->
                assert(articleResponse.articles[i].mapToArticleDTO() == article)
            }
        }
    }
}