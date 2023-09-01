package com.example.imdb_app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import java.time.LocalDate
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreImpl(context: Context) : DataStorePref {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DataStore")
    private val dataStore = context.dataStore

    override suspend fun saveDate(value: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.CURRENT_DATE_KEY] = value
        }
    }

    override suspend fun getDate(): String {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKeys.CURRENT_DATE_KEY] ?: LocalDate.now().toString()
        }.first()
    }

    private object PreferencesKeys {
        val CURRENT_DATE_KEY = stringPreferencesKey("current_date")
    }
}

interface DataStorePref {
    suspend fun saveDate(value: String)
    suspend fun getDate(): String
}
