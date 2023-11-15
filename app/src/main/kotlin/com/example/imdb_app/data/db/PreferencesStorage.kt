package com.example.imdb_app.data.db

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {

    val date: Flow<String?>
    val isLoggedIn: Flow<Boolean>
    val currentAccountLoggedIn: Flow<String>

    suspend fun saveDate(value: String)

    suspend fun saveLoggedInState(value: Boolean)

    suspend fun saveCurrentAccountLoggedIn(value: String)

    object PreferencesKey {
        val CURRENT_DATE_KEY = stringPreferencesKey("CURRENT_DATE")
        val IS_LOGGED_IN_CHECK_KEY = booleanPreferencesKey("IS_LOGGED_IN_CHECK")
        val CURRENT_ACCOUNT_LOGGED_IN_KEY = stringPreferencesKey("CURRENT_ACCOUNT_LOGGED_IN")
    }
}
