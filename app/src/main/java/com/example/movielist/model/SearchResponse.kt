package com.example.movielist.model

import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("Search")
    val search: ArrayList<Movie>?,
    @SerializedName("totalResults")
    val totalResults: String?,
    @SerializedName("Response")
    val response: String?,
)

