package com.sideki.imdb_app.ui.movie_info

import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.domain.use_case.GetMovieInfoUseCase
import com.sideki.imdb_app.model.model.MovieInfoModel
import com.sideki.imdb_app.ui.movie_info.MovieInfoAction.OnActorImageClicked
import com.sideki.imdb_app.ui.movie_info.MovieInfoAction.OnBackButtonClicked
import com.sideki.imdb_app.ui.movie_info.MovieInfoEffect.OpenActorInfoScreen
import com.sideki.imdb_app.ui.movie_info.MovieInfoEffect.OpenMoviesScreen
import com.sideki.imdb_app.util.base.BaseMVIViewModel
import com.sideki.imdb_app.util.base.UIAction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MovieInfoVM @Inject constructor(
    private var getMovieInfoUseCase: GetMovieInfoUseCase
) : BaseMVIViewModel<MovieInfoState>(MovieInfoState()) {

    var movieInfo = MutableStateFlow((MovieInfoModel()))

    override fun handleAction(action: UIAction) {
        when (action) {
            is OnBackButtonClicked -> openMoviesScreen()
            is OnActorImageClicked -> openActorInfoScreen(action.actorId)
        }
    }

    fun getMovieInfo(id: String) {
        viewModelScope.launch {
            movieInfo.value = getMovieInfoUseCase.getMovieInfo(id)
        }
    }

    private fun openMoviesScreen() = setEffect(OpenMoviesScreen())

    private fun openActorInfoScreen(actorId: String){
        setEffect(OpenActorInfoScreen(actorId))
    }
}
