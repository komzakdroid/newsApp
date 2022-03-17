package com.limuealimi.newsapp.domain.useCaseImpl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.limuealimi.newsapp.MainCoroutineRule
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.data.repository.MainRepository
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCaseImpl
import com.limuealimi.newsapp.utils.DefaultDispatcherProvider
import com.limuealimi.newsapp.wheneverBlocking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule


@ExperimentalCoroutinesApi
class ArticleCardUseCaseImplTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: ArticleCardUseCaseImpl

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var repository: MainRepository

    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        subject = ArticleCardUseCaseImpl(
            repository = repository,
            dispatchers = DefaultDispatcherProvider()
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `request success returns list of the articles`() {
        runBlocking {
            val articles: List<Article> = fixture()
            wheneverBlocking { repository.getArticles() }.thenReturn(Result.success(articles))
            val result = subject.loadArticlesData()
            assert(result.isSuccess)
            articles.forEachIndexed { index, article ->
                assert(result.getOrNull()!![index] == article)
            }
        }
    }
}