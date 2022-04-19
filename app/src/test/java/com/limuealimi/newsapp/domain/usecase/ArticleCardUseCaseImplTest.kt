package com.limuealimi.newsapp.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.map
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.limuealimi.newsapp.MainCoroutineRule
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.data.repository.MainRepository
import com.limuealimi.newsapp.utils.DefaultDispatcherProvider
import com.limuealimi.newsapp.wheneverBlocking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
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
    fun `request success returns data of the articles`() {
        runBlocking {
            val articleList = ArrayList<Article>()
            val articles: Flow<PagingData<Article>> = fixture() { 2 }
            wheneverBlocking { repository.getArticles("q") }.thenReturn(articles)
            subject.loadSearchedArticleData("q").collectLatest { it ->
                it.map { article ->
                    articleList.add(article)
                }
            }

            articles.collectLatest { it ->
                it.map { article ->
                    articleList.add(article)
                }
            }
            assert(articleList[0] == articleList[1])
            //assertEquals(articleList[0], articleList[1])
        }
    }
}