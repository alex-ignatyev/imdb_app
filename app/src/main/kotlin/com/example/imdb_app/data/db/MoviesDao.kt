package com.example.imdb_app.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imdb_app.data.db.entity.MovieEntity
import com.example.imdb_app.data.db.entity.MovieEntity.MovieType

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie_table WHERE type LIKE :type")
    suspend fun getMoviesByType(type: MovieType): List<MovieEntity>

    @Query("DELETE FROM movie_table")
    suspend fun clearAllMovies()
}
