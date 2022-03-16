package com.limuealimi.newsapp.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.limuealimi.newsapp.MainCoroutineRule
import com.limuealimi.newsapp.data.api.ApiService
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCaseImpl
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.data.repository.MainRepository
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCase
import com.limuealimi.newsapp.utils.State
import com.nhaarman.mockitokotlin2.mock
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
class HomeViewModelTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loadArticleCardUseCase: ArticleCardUseCase

    @Mock
    private lateinit var api: ApiService

    //private lateinit var viewModel: HomeViewModel

    private val viewModel by lazy { HomeViewModel(loadArticleCardUseCase) }

    val observerState = mock<Observer<State<Result<List<Article>>>>>()

    @Mock
    private lateinit var repository: MainRepository

    private val testDispatcher = TestCoroutineDispatcher()

    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        loadArticleCardUseCase = ArticleCardUseCaseImpl(
            repository = repository,
            dispatcher = testDispatcher
        )
        api = fixture<ApiService>()
        //viewModel = HomeViewModel(loadArticleCardUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getArticlesData() is called, should load data`() {
        runBlocking {
          //  wheneverBlocking { viewModel.getArticlesData() }.thenReturn()
        }
    }
}