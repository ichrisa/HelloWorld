package com.chrisa.helloworld.home.data

import com.chrisa.helloworld.home.data.api.GreetingApi
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val greetingApi: GreetingApi
) {
    suspend fun loadGreeting(): String {
        val greeting = greetingApi.getGreeting()
        return greeting.message
    }
}