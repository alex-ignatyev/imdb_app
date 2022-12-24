package com.sideki.imdb_app.ui.movies_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.data.api.ImdbApi
import com.sideki.imdb_app.domain.model.MovieDataModel
import com.sideki.imdb_app.domain.model.MoviesGroupTitleModel
import com.sideki.imdb_app.domain.model.toDomain
import com.sideki.imdb_app.util.recycler.AdapterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesVM @Inject constructor(
    private val api: ImdbApi
) : ViewModel() {

    val movies: MutableLiveData<List<AdapterItem>> = MutableLiveData(emptyList())

    init {
        viewModelScope.launch {
            val responseTopMovies = api.getMovies().toDomain()
            val response250Movies = api.getTop250Movies().toDomain()
            val list = mutableListOf<AdapterItem>()
            list.add(MoviesGroupTitleModel("Most popular movies"))
            list.add(MovieDataModel(movies = responseTopMovies.movies))
            list.add(MoviesGroupTitleModel("Top 250 movies"))
            list.add(MovieDataModel(movies = response250Movies.movies))
            movies.value = list
        }
    }
}
