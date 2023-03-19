package com.sideki.imdb_app.db

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.sideki.imdb_app.db.PreferenceStorage.PreferencesKey.CURRENT_ACCOUNT_LOGGED_IN_KEY
import com.sideki.imdb_app.db.PreferenceStorage.PreferencesKey.CURRENT_DATE_KEY
import com.sideki.imdb_app.db.PreferenceStorage.PreferencesKey.IS_LOGGED_IN_CHECK_KEY
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class DataStorePreferenceStorage @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferenceStorage {

    override val date: Flow<String> = dataStore.data.map { it[CURRENT_DATE_KEY] ?: "" }
    override val isLoggedIn: Flow<Boolean> = dataStore.data.map { it[IS_LOGGED_IN_CHECK_KEY] ?: false }
    override val currentAccountLoggedIn: Flow<String> = dataStore.data.map { it[CURRENT_ACCOUNT_LOGGED_IN_KEY] ?: "" }

    override suspend fun saveDate(value: String) {
        dataStore.edit { date ->
            date[CURRENT_DATE_KEY] = value
        }
    }

    override suspend fun saveLoggedInState(value: Boolean) {
        dataStore.edit { isLoggedIn ->
            isLoggedIn[IS_LOGGED_IN_CHECK_KEY] = value
        }
    }

    override suspend fun saveCurrentAccountLoggedIn(value: String) {
        dataStore.edit { currentAccountLoggedIn ->
            currentAccountLoggedIn[CURRENT_ACCOUNT_LOGGED_IN_KEY] = value
        }
    }

    companion object {
        const val PREFERENCES_NAME = "PREFERENCES"
    }
}
