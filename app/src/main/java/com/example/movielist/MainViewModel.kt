package com.example.movielist

import androidx.lifecycle.ViewModel
import com.example.movielist.repository.SearchRepository

class MainViewModel: ViewModel() {
    suspend fun getMovie(s: String) {
        SearchRepository.getInstance().fetchMovie(1, "marvel")
    }
}