package com.chrisa.helloworld.home.data

import com.chrisa.helloworld.home.data.api.GreetingApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
internal object DataModule {

    @JvmStatic
    @Provides
    fun moshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Singleton
    @JvmStatic
    @Provides
    fun okHttpClient(): OkHttpClient {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)

        return builder.build()
    }

    @JvmStatic
    @Provides
    fun retrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @JvmStatic
    @Provides
    fun cakeApi(retrofit: Retrofit): GreetingApi {
        return retrofit.create(GreetingApi::class.java)
    }
}