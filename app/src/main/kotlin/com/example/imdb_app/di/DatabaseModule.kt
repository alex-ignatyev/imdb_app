package com.example.imdb_app.di

import android.content.Context
import androidx.room.Room
import com.example.imdb_app.data.DataStoreImpl
import com.example.imdb_app.data.DataStorePref
import com.example.imdb_app.data.db.AccountsDao
import com.example.imdb_app.data.db.MoviesDB
import com.example.imdb_app.data.db.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStorePref = DataStoreImpl(context)

    @Provides
    @Singleton
    fun provideMoviesDB(@ApplicationContext context: Context): MoviesDB {
        return Room.databaseBuilder(
            context,
            MoviesDB::class.java,
            "MoviesDB"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(moviesDb: MoviesDB): MoviesDao = moviesDb.moviesDao()

    @Provides
    @Singleton
    fun provideAccountsDao(accountsDb: MoviesDB): AccountsDao = accountsDb.accountsDao()
}
