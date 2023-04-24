package com.sideki.imdb_app.ui.movie_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.data.api.ImdbApi
import com.sideki.imdb_app.db.entity.SelectedMoviesEntity
import com.sideki.imdb_app.db.entity.toEntity
import com.sideki.imdb_app.domain.model.MovieInfoModel
import com.sideki.imdb_app.domain.model.toDomain
import com.sideki.imdb_app.domain.use_case.SelectedMoviesRepository
import com.sideki.imdb_app.ui.movie_info.MovieInfoAction.OnAddMovieClicked
import com.sideki.imdb_app.ui.movie_info.MovieInfoAction.OnBackButtonClicked
import com.sideki.imdb_app.ui.movie_info.MovieInfoEffect.OpenMoviesScreen
import com.sideki.imdb_app.util.base.BaseMVIViewModel
import com.sideki.imdb_app.util.base.UIAction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class MovieInfoVM @Inject constructor(
    private val api: ImdbApi,
    private val repo: SelectedMoviesRepository
) : BaseMVIViewModel<MovieInfoState>(MovieInfoState()) {

    var data by mutableStateOf((MovieInfoModel()))

    override fun handleAction(action: UIAction) {
        when(action){
            is OnBackButtonClicked -> openMoviesScreen()
            is OnAddMovieClicked -> insertMovie(data.movieId)
        }
    }

    fun getMovieInfo(id: String) {
        viewModelScope.launch {
            val response = api.getMovieInfo(titleId = id)
            data = response.toDomain()
        }
    }

    fun insertMovie(id: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = api.getMovieInfo(titleId = id)
                repo.insertMovie(response.toEntity())
            }
        }
    }

    private fun openMoviesScreen() = setEffect(OpenMoviesScreen())
}
