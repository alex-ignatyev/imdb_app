package com.sideki.imdb_app.ui.movie_info

import com.sideki.imdb_app.model.model.MovieInfoModel
import com.sideki.imdb_app.util.base.UIAction
import com.sideki.imdb_app.util.base.UIEffect
import com.sideki.imdb_app.util.base.UIState

sealed class MovieInfoAction : UIAction {
    class OnBackButtonClicked : MovieInfoAction()
    class OnActorImageClicked: MovieInfoAction()
}

data class MovieInfoState(
    val movieInfo: MovieInfoModel = MovieInfoModel()
) : UIState

sealed class MovieInfoEffect : UIEffect {
    class OpenMoviesScreen : MovieInfoEffect()
    class OpenActorInfoScreen: MovieInfoEffect()
}
