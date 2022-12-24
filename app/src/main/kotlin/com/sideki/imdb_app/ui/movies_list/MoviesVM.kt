package com.sideki.imdb_app.ui.movies_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.data.api.ImdbApi
import com.sideki.imdb_app.domain.model.MovieDataModel
import com.sideki.imdb_app.domain.model.MovieListScreenModel
import com.sideki.imdb_app.domain.model.MoviesGroupTitleModel
import com.sideki.imdb_app.domain.model.toDomain
import com.sideki.imdb_app.util.recycler.AdapterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesVM @Inject constructor(private val api: ImdbApi): ViewModel() {

    val movies: MutableLiveData<List<MovieListScreenModel>> = MutableLiveData(emptyList())

    init {
        viewModelScope.launch {
            val response = api.getMovies().toDomain()
            movies.value = listOf(MovieListScreenModel(title = MoviesGroupTitleModel("Most popular movies"), movieList = response.movies))
        }
    }
}
