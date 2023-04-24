package com.sideki.imdb_app.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.db.DataStorePreferenceStorage
import com.sideki.imdb_app.db.entity.SelectedMoviesEntity
import com.sideki.imdb_app.domain.model.MovieInfoModel
import com.sideki.imdb_app.domain.use_case.SelectedMoviesRepository
import com.sideki.imdb_app.ui.profile.ProfileAction.OnChangePasswordTextClicked
import com.sideki.imdb_app.ui.profile.ProfileAction.OnLogOutTextClicked
import com.sideki.imdb_app.ui.profile.ProfileEffect.OpenChangePasswordScreen
import com.sideki.imdb_app.ui.profile.ProfileEffect.OpenLoginScreen
import com.sideki.imdb_app.util.base.BaseMVIViewModel
import com.sideki.imdb_app.util.base.UIAction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class ProfileVM @Inject constructor(
    private val preferences: DataStorePreferenceStorage,
    private val repo: SelectedMoviesRepository
) : BaseMVIViewModel<ProfileState>(ProfileState()) {

    var data by mutableStateOf(List<SelectedMoviesEntity>())

    override fun handleAction(action: UIAction) {
        when (action) {
            is OnChangePasswordTextClicked -> setEffect(OpenChangePasswordScreen())
            is OnLogOutTextClicked -> logOut()
        }
    }

    fun getSelectedMovies(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                data = repo.getSelectedMovies()
            }
        }
    }

    private fun logOut() {
        viewModelScope.launch {
            preferences.saveLoggedInState(false)
            setEffect(OpenLoginScreen())
        }
    }
}
