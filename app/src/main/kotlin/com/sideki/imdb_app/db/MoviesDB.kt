package com.sideki.imdb_app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sideki.imdb_app.db.entity.AccountEntity
import com.sideki.imdb_app.db.entity.MovieEntity

@Database(entities = [MovieEntity::class, AccountEntity::class], version = 1)
abstract class MoviesDB: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun accountDao(): AccountDao
}
