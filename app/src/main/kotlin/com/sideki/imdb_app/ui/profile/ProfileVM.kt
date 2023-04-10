package com.sideki.imdb_app.ui.profile

import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.db.DataStorePreferenceStorage
import com.sideki.imdb_app.ui.profile.ProfileAction.OnChangePasswordTextClicked
import com.sideki.imdb_app.ui.profile.ProfileAction.OnLogOutTextClicked
import com.sideki.imdb_app.ui.profile.ProfileEffect.OpenChangePasswordScreen
import com.sideki.imdb_app.ui.profile.ProfileEffect.OpenLoginScreen
import com.sideki.imdb_app.util.base.BaseMVIViewModel
import com.sideki.imdb_app.util.base.UIAction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileVM @Inject constructor(

) : BaseMVIViewModel<ProfileState>(ProfileState()) {

    var preferences: DataStorePreferenceStorage? = null

    override fun handleAction(action: UIAction) {
        when (action) {
            is OnChangePasswordTextClicked -> setEffect(OpenChangePasswordScreen())
            is OnLogOutTextClicked -> logOut()
        }
    }

    fun logOut() {
        viewModelScope.launch {
            preferences?.saveLoggedInState(false)
            setEffect(OpenLoginScreen())
        }
    }
}
