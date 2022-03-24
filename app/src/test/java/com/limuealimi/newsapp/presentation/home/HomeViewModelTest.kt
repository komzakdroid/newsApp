package com.limuealimi.newsapp.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.limuealimi.newsapp.MainCoroutineRule
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
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
    private lateinit var viewModel: HomeViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    /*private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }*/

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when loadArticlesData() is called, should load data`() =
        mainCoroutineRule.testDispatcher.runBlockingTest {
            viewModel = HomeViewModel(loadArticleCardUseCase)
            // wheneverBlocking { loadArticleCardUseCase.loadArticlesData() }.thenReturn(fixture())
            loadArticleCardUseCase.loadArticlesData(1).onSuccess { emptyList<Article>() }

        }
}