package com.limuealimi.newsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

@ExperimentalCoroutinesApi
class MainCoroutineRule(
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}

fun <T> wheneverBlocking(methodCall: suspend () -> T): OngoingStubbing<T> {
    return runBlocking { Mockito.`when`(methodCall()) }
}

fun <T> observe(liveData: LiveData<T>) = mock<Observer<T>>().apply {
    liveData.observeForever(this)
}