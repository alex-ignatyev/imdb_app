package com.sideki.imdb_app.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sideki.imdb_app.db.entity.SelectedMoviesEntity

@Dao
interface SelectedMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSelectedMovie(movie: SelectedMoviesEntity)

    @Query("SELECT * FROM selected_movies_table")
    suspend fun getSelectedMovies(): List<SelectedMoviesEntity>

    @Query("SELECT * FROM selected_movies_table WHERE id LIKE :id")
    suspend fun getSelectedMovie(id: String): SelectedMoviesEntity

    @Delete
    suspend fun deleteSelectedMovie(movie: SelectedMoviesEntity)
}
