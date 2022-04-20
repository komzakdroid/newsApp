package com.limuealimi.newsapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.map
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.limuealimi.newsapp.MainCoroutineRule
import com.limuealimi.newsapp.data.network.api.ApiService
import com.limuealimi.newsapp.data.network.model.ArticleResponseDTO
import com.limuealimi.newsapp.utils.toArticle
import com.limuealimi.newsapp.wheneverBlocking
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.Response

@ExperimentalCoroutinesApi
class MainRepositoryImplTest {
    /**
    TODO:
     * How to mock API service?
     * How to check methods with mock response?
     * How to mock Repository and call api service with error result?
     * How to test and check LiveData?
     */

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @InjectMocks
    private lateinit var subject: MainRepositoryImpl

    @Mock
    private lateinit var apiService: ApiService

    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
    }


    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getArticles in api service getting called one time`() {
        runBlocking {
            wheneverBlocking { apiService.everything() }.thenReturn(fixture())
            subject.getArticles("q").collectLatest { }
            verify(apiService, times(1)).everything()
        }
    }

    @Test
    fun `getArticles passes correct data`() {
        runBlocking {
            val articleResponse: Response<ArticleResponseDTO> = fixture()
            wheneverBlocking { apiService.everything() }.thenReturn(articleResponse)
            subject.getArticles("q").collectLatest {
                it.map { it ->
                    articleResponse.body()?.articles!!.forEach { i ->
                        assert(it == i.toArticle())
                    }
                }
            }
        }
    }

}