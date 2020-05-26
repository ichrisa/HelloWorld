package com.chrisa.helloworld.home.data

import com.chrisa.helloworld.home.data.api.Greeting
import com.chrisa.helloworld.home.data.api.GreetingApi
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

class HomeDataSourceTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)
    private val api = mockk<GreetingApi>(relaxed = true)

    private lateinit var sut: HomeDataSource

    @Before
    fun setup() {
        sut = HomeDataSource(api)
    }

    @Test
    fun `WHEN api call succeeds THEN message string is returned`() =
        testCoroutineScope.runBlockingTest {
            // GIVEN
            val message = "Hello"
            coEvery { api.getGreeting() } returns Greeting(message = message)
            // WHEN
            val greeting = sut.loadGreeting()
            // THEN
            assertThat(greeting).isEqualTo("Hello")
        }

    @Test(expected = IOException::class)
    fun `WHEN api call errors THEN error is emitted`() =
        testCoroutineScope.runBlockingTest {
            // GIVEN
            val error = IOException()
            coEvery { api.getGreeting() } throws error
            // WHEN
            sut.loadGreeting()
        }
}
