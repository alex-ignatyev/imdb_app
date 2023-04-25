package com.sideki.imdb_app.ui.movie_info

import com.sideki.imdb_app.domain.model.MovieInfoModel
import com.sideki.imdb_app.util.base.UIAction
import com.sideki.imdb_app.util.base.UIEffect
import com.sideki.imdb_app.util.base.UIState

sealed class MovieInfoAction : UIAction {
    class OnBackButtonClicked : MovieInfoAction()
    data class OnAddMovieClicked(val movie: MovieInfoModel) : MovieInfoAction()
    data class OnDeleteMovieClicked(val movie: MovieInfoModel) : MovieInfoAction()
}

data class MovieInfoState(
    val data: MovieInfoModel = MovieInfoModel(),
    val isMovieAdded: Boolean = false
) : UIState

sealed class MovieInfoEffect : UIEffect {
    class OpenMoviesScreen : MovieInfoEffect()
}
