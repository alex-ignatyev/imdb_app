package com.example.imdb_app.ui.movie_info

import com.example.imdb_app.domain.model.MovieInfoModel
import com.example.imdb_app.util.base.UIAction
import com.example.imdb_app.util.base.UIEffect
import com.example.imdb_app.util.base.UIState

sealed class MovieInfoAction : UIAction {
    class OnBackButtonClicked : MovieInfoAction()
    data class OnActorImageClicked(val actorId: String) : MovieInfoAction()
}

data class MovieInfoState(
    val movieInfo: MovieInfoModel = MovieInfoModel()
) : UIState

sealed class MovieInfoEffect : UIEffect {
    class OpenMoviesScreen : MovieInfoEffect()
    data class OpenActorInfoScreen(val actorId: String) : MovieInfoEffect()
}
