package com.sideki.imdb_app.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.first

@Singleton
class DataStorePreferences @Inject constructor(
    @ApplicationContext context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "date")
    private val dataStore = context.dataStore

    suspend fun saveDate(value: String) {
        val dataStoreKey = PreferencesKeys.CURRENT_DATE_KEY
        dataStore.edit { date ->
            date[dataStoreKey] = value
        }
    }

    suspend fun getDate(): String? {
        val dataStoreKey = PreferencesKeys.CURRENT_DATE_KEY
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

    private object PreferencesKeys {
        val CURRENT_DATE_KEY = stringPreferencesKey("CURRENT_DATE")
    }
}
