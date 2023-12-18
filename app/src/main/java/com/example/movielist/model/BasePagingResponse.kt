package com.example.movielist.model

import com.google.gson.annotations.SerializedName

class BasePagingResponse<out T>(
    @SerializedName("Search") val data: T,
    @SerializedName("totalResults") val totalResults: String?,
    @SerializedName("Response") val response: String?,
)