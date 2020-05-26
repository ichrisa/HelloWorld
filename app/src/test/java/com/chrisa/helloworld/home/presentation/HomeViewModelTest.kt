package com.chrisa.helloworld.home.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chrisa.core.util.test
import com.chrisa.helloworld.home.data.HomeDataSource
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)
    private val dataSource = mockk<HomeDataSource>(relaxed = true)
    private lateinit var sut: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        sut = HomeViewModel(
            homeDataSource = dataSource,
            dispatcher = Dispatchers.Main
        )
    }

    @Test
    fun `WHEN greeting succeeds THEN message is emitted`() {
        // GIVEN
        val message = "Hello"
        coEvery { dataSource.loadGreeting() } returns "Hello"
        val greetings = sut.greetingResource.test()
        // WHEN
        sut.loadGreeting()
        // THEN
        assertThat(greetings.values[0]).isEqualTo(GreetingResource.Success(message))
    }

    @Test
    fun `WHEN greeting fails THEN error is emitted`() {
        // GIVEN
        val error = IOException("Error loading greeting")
        coEvery { dataSource.loadGreeting() } throws error
        val greetings = sut.greetingResource.test()
        // WHEN
        sut.loadGreeting()
        // THEN
        assertThat(greetings.values[0]).isEqualTo(GreetingResource.Error)
    }
}
