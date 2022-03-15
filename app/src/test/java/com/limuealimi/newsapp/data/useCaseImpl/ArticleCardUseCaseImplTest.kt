package com.limuealimi.newsapp.data.useCaseImpl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.limuealimi.newsapp.MainCoroutineRule
import com.limuealimi.newsapp.domain.repository.MainRepository
import com.limuealimi.newsapp.wheneverBlocking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
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

    @InjectMocks
    private lateinit var useCase: ArticleCardUseCaseImpl

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var repository: MainRepository

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
    fun `should past be empty`() {
        runBlocking {
            wheneverBlocking { repository.getArticles().getOrNull() }.thenReturn(emptyList())
            val articleEvents = useCase.loadArticlesData().getOrNull()
            assert(articleEvents?.isEmpty() == true)
        }
    }
}