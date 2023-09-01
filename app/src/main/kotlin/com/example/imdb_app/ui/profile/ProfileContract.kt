package com.example.imdb_app.ui.profile

import com.example.imdb_app.util.base.UIAction
import com.example.imdb_app.util.base.UIEffect
import com.example.imdb_app.util.base.UIState

sealed class ProfileAction : UIAction {
    class OnChangePasswordTextClicked : ProfileAction()
    class OnLogOutTextClicked : ProfileAction()
}

class ProfileState : UIState

sealed class ProfileEffect : UIEffect {
    class OpenChangePasswordScreen : ProfileEffect()
    class OpenLoginScreen : ProfileEffect()
}
