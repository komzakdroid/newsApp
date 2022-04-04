package com.limuealimi.newsapp.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.limuealimi.newsapp.MainCoroutineRule
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Rule
import org.mockito.Mock
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
}