package com.sideki.imdb_app.ui.movie_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.data.api.ImdbApi
import com.sideki.imdb_app.domain.model.MovieInfoModel
import com.sideki.imdb_app.domain.model.toDomain
import com.sideki.imdb_app.ui.movie_info.MovieInfoAction.OnBackButtonClicked
import com.sideki.imdb_app.ui.movie_info.MovieInfoEffect.OpenMoviesScreen
import com.sideki.imdb_app.util.base.BaseMVIViewModel
import com.sideki.imdb_app.util.base.UIAction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MovieInfoVM @Inject constructor(
    private var getMovieInfoUseCase: GetMovieInfoUseCase
) : ViewModel() {
    private var api: ImdbApi
) : BaseMVIViewModel<MovieInfoState>(MovieInfoState()) {

    var movieInfo = MutableStateFlow((MovieInfoModel()))

    fun getMovieInfo(movieId: String) {
    override fun handleAction(action: UIAction) {
        when(action){
            is OnBackButtonClicked -> openMoviesScreen()
        }
    }

    fun getMovieInfo(id: String) {
        viewModelScope.launch {
            movieInfo.value = getMovieInfoUseCase.getMovieInfo(movieId)
        }
    }

    private fun openMoviesScreen() = setEffect(OpenMoviesScreen())
}
