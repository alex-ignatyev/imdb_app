package com.sideki.imdb_app.ui.movie_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.data.api.ImdbApi
import com.sideki.imdb_app.db.entity.toEntity
import com.sideki.imdb_app.domain.model.MovieInfoModel
import com.sideki.imdb_app.domain.model.toDomain
import com.sideki.imdb_app.domain.use_case.DeleteSelectedMovieUseCase
import com.sideki.imdb_app.domain.use_case.GetSelectedMovieUseCase
import com.sideki.imdb_app.domain.use_case.InsertSelectedMovieUseCase
import com.sideki.imdb_app.ui.movie_info.MovieInfoAction.OnAddMovieClicked
import com.sideki.imdb_app.ui.movie_info.MovieInfoAction.OnBackButtonClicked
import com.sideki.imdb_app.ui.movie_info.MovieInfoAction.OnDeleteMovieClicked
import com.sideki.imdb_app.ui.movie_info.MovieInfoEffect.OpenMoviesScreen
import com.sideki.imdb_app.util.base.BaseMVIViewModel
import com.sideki.imdb_app.util.base.UIAction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MovieInfoVM @Inject constructor(
    private val api: ImdbApi,
    private val insertSelectedMovieUseCase: InsertSelectedMovieUseCase,
    private val getSelectedMovieUseCase: GetSelectedMovieUseCase,
    private val deleteSelectedMovieUseCase: DeleteSelectedMovieUseCase
) : BaseMVIViewModel<MovieInfoState>(MovieInfoState()) {

    var movieInfo by mutableStateOf((MovieInfoModel()))

    override fun handleAction(action: UIAction) {
        when (action) {
            is OnBackButtonClicked -> openMoviesScreen()
            is OnAddMovieClicked -> insertMovie(action.movie)
            is OnDeleteMovieClicked -> deleteMovie(action.movie)
        }
    }

    fun getMovieInfo(id: String) {
        viewModelScope.launch {
            val response = api.getMovieInfo(titleId = id)
            movieInfo = response.toDomain()
        }
    }

    fun checkSelectedMovie(id: String) {
        viewModelScope.launch {
            println("Is movie added to selected??? ${movieInfo.toEntity() == getSelectedMovieUseCase.getSelectedMovie(id)}")
            setState(
                currentState.copy(isMovieAdded = movieInfo.toEntity() == getSelectedMovieUseCase.getSelectedMovie(id))
            )
            println(" State is ${currentState.isMovieAdded}")
        }
    }

    private fun insertMovie(movie: MovieInfoModel) {
        viewModelScope.launch {
            insertSelectedMovieUseCase.insertSelectedMovie(movie.toEntity())
        }
    }

    private fun deleteMovie(movie: MovieInfoModel) {
        viewModelScope.launch {
            deleteSelectedMovieUseCase.deleteSelectedMovie(movie.toEntity())
        }
    }

    private fun openMoviesScreen() {
        setEffect(OpenMoviesScreen())
    }
}
