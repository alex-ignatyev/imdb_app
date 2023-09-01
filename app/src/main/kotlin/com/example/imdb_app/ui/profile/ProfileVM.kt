package com.example.imdb_app.ui.profile

import androidx.lifecycle.viewModelScope
import com.example.imdb_app.db.DataStorePreferenceStorage
import com.example.imdb_app.ui.profile.ProfileAction.OnChangePasswordTextClicked
import com.example.imdb_app.ui.profile.ProfileAction.OnLogOutTextClicked
import com.example.imdb_app.ui.profile.ProfileEffect.OpenChangePasswordScreen
import com.example.imdb_app.ui.profile.ProfileEffect.OpenLoginScreen
import com.example.imdb_app.util.base.BaseMVIViewModel
import com.example.imdb_app.util.base.UIAction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileVM @Inject constructor(
    private val preferences: DataStorePreferenceStorage
) : BaseMVIViewModel<ProfileState>(ProfileState()) {

    override fun handleAction(action: UIAction) {
        when (action) {
            is OnChangePasswordTextClicked -> setEffect(OpenChangePasswordScreen())
            is OnLogOutTextClicked -> logOut()
        }
    }

    private fun logOut() {
        viewModelScope.launch {
            preferences.saveLoggedInState(false)
            setEffect(OpenLoginScreen())
        }
    }
}
