package com.sideki.imdb_app.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.db.DataStorePreferenceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileVM @Inject constructor(
    private val preferences: DataStorePreferenceStorage
) : ViewModel() {

    fun logOut() {
        viewModelScope.launch {
            preferences.saveLoggedInState(false)
        }
    }
}
