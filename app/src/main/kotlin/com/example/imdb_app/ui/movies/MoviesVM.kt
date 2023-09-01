package com.example.imdb_app.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb_app.domain.use_case.ClearAllMoviesUseCase
import com.example.imdb_app.domain.use_case.GetMoviesUseCase
import com.example.imdb_app.util.recycler.AdapterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesVM @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val clearAllMoviesUseCase: ClearAllMoviesUseCase
) : ViewModel() {

    val movies: MutableLiveData<List<AdapterItem>> = MutableLiveData(emptyList())

    init {
        viewModelScope.launch {
            clearAllMoviesUseCase.clearAllMoviesIfNeeded()
            movies.value = getMoviesUseCase.getMovies()
        }
    }
}
