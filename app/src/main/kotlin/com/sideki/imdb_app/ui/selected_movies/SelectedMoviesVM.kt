package com.sideki.imdb_app.ui.selected_movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.db.entity.SelectedMoviesEntity
import com.sideki.imdb_app.domain.use_case.GetSelectedMoviesUseCase
import com.sideki.imdb_app.ui.movie_info.MovieInfoEffect.OpenMoviesScreen
import com.sideki.imdb_app.ui.profile.ProfileState
import com.sideki.imdb_app.ui.selected_movies.SelectedMovieAction.OnBackButtonClicked
import com.sideki.imdb_app.util.base.BaseMVIViewModel
import com.sideki.imdb_app.util.base.UIAction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SelectedMoviesVM @Inject constructor(
    private val getSelectedMoviesUseCase: GetSelectedMoviesUseCase
) : BaseMVIViewModel<ProfileState>(ProfileState()) {

    var data by mutableStateOf<List<SelectedMoviesEntity>>(listOf())

    override fun handleAction(action: UIAction) {
        when (action) {
            is OnBackButtonClicked -> openProfileScreen()
        }
    }

    fun getSelectedMovies() {
        viewModelScope.launch {
            data = getSelectedMoviesUseCase.getSelectedMovies()
        }
    }

    private fun openProfileScreen() {
        setEffect(OpenMoviesScreen())
    }
}
