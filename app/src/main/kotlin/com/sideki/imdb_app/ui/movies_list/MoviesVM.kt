package com.sideki.imdb_app.ui.movies_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.data.api.ImdbApi
import com.sideki.imdb_app.data.response.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesVM @Inject constructor(private val api: ImdbApi): ViewModel() {

     val movies= MutableLiveData<List<MovieResponse>>(null)

    init {
        viewModelScope.launch {
            val response = api.getMovies()
            movies.value = response.movies
        }
    }
}
