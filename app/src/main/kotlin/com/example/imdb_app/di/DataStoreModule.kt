package com.example.imdb_app.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.imdb_app.data.db.DataStorePreferenceStorage
import com.example.imdb_app.data.db.DataStorePreferenceStorage.Companion.PREFERENCES_NAME
import com.example.imdb_app.data.db.PreferenceStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    val Context.datastore by preferencesDataStore(
        name = PREFERENCES_NAME,
        produceMigrations = { context ->
            listOf(SharedPreferencesMigration(context, PREFERENCES_NAME))
        }
    )

    @Singleton
    @Provides
    fun provideDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> {

        val datastore = PreferenceDataStoreFactory.create(
            migrations = listOf(SharedPreferencesMigration(context, PREFERENCES_NAME)),
            scope = CoroutineScope(Dispatchers.Default)
        ) {
            context.preferencesDataStoreFile(PREFERENCES_NAME)
        }
        return datastore
    }

    @Singleton
    @Provides
    fun providePreferencesStorage(@ApplicationContext context: Context): PreferenceStorage =
        DataStorePreferenceStorage(context.datastore)
}
