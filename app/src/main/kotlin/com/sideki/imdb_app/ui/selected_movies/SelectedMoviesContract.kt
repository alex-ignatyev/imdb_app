package com.sideki.imdb_app.ui.selected_movies

import com.sideki.imdb_app.util.base.UIAction
import com.sideki.imdb_app.util.base.UIEffect
import com.sideki.imdb_app.util.base.UIState

sealed class SelectedMovieAction : UIAction {
    class OnBackButtonClicked : SelectedMovieAction()
    class OnSelectedMovieClicked : SelectedMovieAction()
}

class SelectedMoviesState : UIState

sealed class ProfileEffect : UIEffect {
    class OpenChangePasswordScreen : ProfileEffect()
}
