package com.sideki.imdb_app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sideki.imdb_app.db.entity.AccountEntity
import com.sideki.imdb_app.db.entity.MovieEntity
import com.sideki.imdb_app.db.entity.SelectedMoviesEntity

@Database(entities = [MovieEntity::class, AccountEntity::class, SelectedMoviesEntity::class], version = 1)
abstract class MoviesDB: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun accountDao(): AccountDao
    abstract fun selectedMoviesDao(): SelectedMoviesDao
}
