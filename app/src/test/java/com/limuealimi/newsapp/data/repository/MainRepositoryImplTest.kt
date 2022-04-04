package com.limuealimi.newsapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.limuealimi.newsapp.MainCoroutineRule
import com.limuealimi.newsapp.data.network.api.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Rule
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
}