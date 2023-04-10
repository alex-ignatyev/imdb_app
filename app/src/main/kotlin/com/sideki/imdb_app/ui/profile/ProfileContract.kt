package com.sideki.imdb_app.ui.profile

import com.sideki.imdb_app.util.base.UIAction
import com.sideki.imdb_app.util.base.UIEffect
import com.sideki.imdb_app.util.base.UIState
import com.sideki.imdb_app.util.recycler.AdapterItem

sealed class ProfileAction: UIAction {
    class OnChangePasswordTextClicked: ProfileAction()
    class OnLogOutTextClicked: ProfileAction()
}

data class ProfileState(
    val selectedMoviesList: List<AdapterItem> = emptyList()
): UIState

sealed class ProfileEffect: UIEffect{
    class OpenChangePasswordScreen: ProfileEffect()
    class OpenLoginScreen: ProfileEffect()
}
