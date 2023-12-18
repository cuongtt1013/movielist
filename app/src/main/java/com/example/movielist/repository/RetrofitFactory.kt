package com.example.movielist.repository

import com.example.movielist.model.SearchResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    private val searchInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("type", "movie")
            .addQueryParameter("apikey", "b9bd48a6")
            .build()
        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
        val build = newRequest.build()
        chain.proceed(build)
    }

    private fun loggingInterceptor() : HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return loggingInterceptor
    }

    private val clientSearch =
        OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor())
            .addInterceptor(searchInterceptor)
            .readTimeout(30000, TimeUnit.MILLISECONDS)
            .writeTimeout(30000, TimeUnit.MILLISECONDS)
            .connectTimeout(60000, TimeUnit.MILLISECONDS)
            .build()

    private val retrofitSearch: Retrofit = Retrofit.Builder()
        .client(clientSearch)
        .baseUrl("http://www.omdbapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> buildServiceSearch(service: Class<T>): T {
        return retrofitSearch.create(service)
    }
}