package com.sideki.imdb_app.ui.movie_info

import com.sideki.imdb_app.model.model.MovieInfoModel
import com.sideki.imdb_app.util.base.UIAction
import com.sideki.imdb_app.util.base.UIEffect
import com.sideki.imdb_app.util.base.UIState

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
