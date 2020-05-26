package com.chrisa.helloworld.home.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.GET

interface GreetingApi {
    @GET("ichrisa/3c35125709a696c86a367f0165775501/raw/5ed89611240486d7e3487ec42a0d59db8347f7e5/helloWorld")
    suspend fun getGreeting(): Greeting
}

@JsonClass(generateAdapter = true)
data class Greeting(
    @Json(name = "message") val message: String
)