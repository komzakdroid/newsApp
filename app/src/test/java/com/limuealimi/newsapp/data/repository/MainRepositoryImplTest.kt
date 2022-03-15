package com.limuealimi.newsapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.limuealimi.newsapp.MainCoroutineRule
import com.limuealimi.newsapp.data.api.ApiService
import com.limuealimi.newsapp.wheneverBlocking
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
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
    private lateinit var repository: MainRepositoryImpl

    @Mock
    private lateinit var apiService: ApiService

    private val testDispatcher = TestCoroutineDispatcher()

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Articles should be empty`() {
        runBlocking {
            wheneverBlocking { apiService.getArticles().articles }.thenReturn(emptyList())
            val articleEvents = repository.getArticles().getOrThrow()
            assert(articleEvents.isEmpty())
        }
    }
}