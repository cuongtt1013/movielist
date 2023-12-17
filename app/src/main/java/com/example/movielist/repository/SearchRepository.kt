package com.example.movielist.repository

import com.example.movielist.model.SearchResponse
import retrofit2.Response

class SearchRepository {

    suspend fun <T> callApi(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            throw Exception()
        } catch (ex: Exception) {
            throw ex
        }
    }

    private var request: SearchApi = RetrofitFactory.buildServiceSearch(SearchApi::class.java)

    suspend fun fetchMovie(page: Int, s: String): Resource<SearchResponse> =
        callApi { request.fetchMovies(page = page, s = s) }

    companion object {
        private var INSTANCE: SearchRepository? = null

        fun getInstance() = INSTANCE
            ?: SearchRepository().also {
                INSTANCE = it
            }
    }
}