package com.sideki.imdb_app.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sideki.imdb_app.db.entity.MovieEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movie_table WHERE type LIKE :type")
    suspend fun getMoviesByType(type: MovieType): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Delete
    suspend fun deleteAllMovies(movies: List<MovieEntity>)
}
