package com.sideki.imdb_app.db

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {

    val date: Flow<String?>
    val isLoggedIn: Flow<Boolean>

    suspend fun saveDate(value: String)

    suspend fun saveLoggedInState(value: Boolean)

    object PreferencesKey {
        val CURRENT_DATE_KEY = stringPreferencesKey("CURRENT_DATE")
        val IS_LOGGED_IN_CHECK_KEY = booleanPreferencesKey("IS_LOGGED_IN_CHECK")
    }
}
