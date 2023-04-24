package com.sideki.imdb_app.di

import android.content.Context
import androidx.room.Room
import com.sideki.imdb_app.db.AccountDao
import com.sideki.imdb_app.db.MoviesDB
import com.sideki.imdb_app.db.MoviesDao
import com.sideki.imdb_app.db.SelectedMoviesDao
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
    fun provideMoviesDB(@ApplicationContext context: Context): MoviesDB = Room.databaseBuilder(
        context,
        MoviesDB::class.java,
        "MoviesDB"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideMoviesDao(moviesDb: MoviesDB): MoviesDao = moviesDb.moviesDao()

    @Provides
    @Singleton
    fun provideAccountDao(accountDb: MoviesDB): AccountDao = accountDb.accountDao()

    @Provides
    @Singleton
    fun provideSelectedMoviesDao(moviesDb: MoviesDB): SelectedMoviesDao = moviesDb.selectedMoviesDao()
}
