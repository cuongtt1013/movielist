package com.example.movielist.adapter

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movielist.model.Movie
import com.example.movielist.repository.SearchRepository
import kotlin.math.roundToInt

class SearchDataSource (
    private val repository: SearchRepository,
    private val s: String
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val position = params.key ?: 1
            val response = repository.fetchMovie(position, s)
            val data = response.data
            val totalPage = data?.totalResults?.toDoubleOrNull()?.div(10)?.roundToInt() ?: -1
            var nextPageNumber: Int? = null
            if (data != null && position <= totalPage) {
                nextPageNumber = position + 1
            }
            LoadResult.Page(
                data = data?.data ?: listOf(),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}