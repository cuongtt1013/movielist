package com.example.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movielist.adapter.SearchDataSource
import com.example.movielist.model.Movie
import com.example.movielist.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel: ViewModel() {

    var searchKeyWord: String = ""

    fun getMovie(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 2,
            ),
            pagingSourceFactory = {
                SearchDataSource(
                    SearchRepository.getInstance(),
                    searchKeyWord
                )
            }
        ).flow.cachedIn(viewModelScope)

    }
}