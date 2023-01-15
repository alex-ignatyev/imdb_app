package com.sideki.imdb_app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sideki.imdb_app.db.entity.MovieEntity
import com.sideki.imdb_app.db.entity.MovieEntity.MovieType

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movie_table WHERE type LIKE :type")
    suspend fun getMoviesByType(type: MovieType): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAllMovies()
}