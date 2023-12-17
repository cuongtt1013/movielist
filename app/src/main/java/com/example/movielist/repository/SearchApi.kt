package com.example.movielist.repository

import com.example.movielist.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET()
    suspend fun fetchMovies(
        @Query("page") page: Int = 1,
        @Query("s") s: String,
    ): Response<SearchResponse>
}