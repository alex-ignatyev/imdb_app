package com.sideki.imdb_app.ui.movies_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.domain.use_case.GetMoviesUseCase
import com.sideki.imdb_app.util.recycler.AdapterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesVM @Inject constructor(
    private val useCase: GetMoviesUseCase
) : ViewModel() {

    val movies: MutableLiveData<List<AdapterItem>> = MutableLiveData(emptyList())

    init {
        viewModelScope.launch {
            movies.value = useCase.getMovies()
        }
    }
}
