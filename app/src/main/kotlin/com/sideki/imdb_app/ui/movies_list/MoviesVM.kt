package com.sideki.imdb_app.ui.movies_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.db.MoviesDao
import com.sideki.imdb_app.di.DataStorePreferences
import com.sideki.imdb_app.domain.use_case.GetMoviesUseCase
import com.sideki.imdb_app.util.recycler.AdapterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesVM @Inject constructor(
    private val useCase: GetMoviesUseCase,
    private val moviesDao: MoviesDao,
    private val dataStorePreferences: DataStorePreferences
) : ViewModel() {

    val movies: MutableLiveData<List<AdapterItem>> = MutableLiveData(emptyList())

    init {
        viewModelScope.launch {
            val currentDate = LocalDate.now().toString()
            val dateFromPreferences = dataStorePreferences.read()
            if (currentDate != dateFromPreferences) {
                moviesDao.clearTable()
            }
            movies.value = useCase.getMovies()
        }
    }
}
