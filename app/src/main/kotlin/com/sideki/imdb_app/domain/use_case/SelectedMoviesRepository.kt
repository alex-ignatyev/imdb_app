package com.sideki.imdb_app.domain.use_case

import com.sideki.imdb_app.db.SelectedMoviesDao
import com.sideki.imdb_app.db.entity.SelectedMoviesEntity
import javax.inject.Inject

class SelectedMoviesRepository @Inject constructor(
    private val selectedMoviesDao: SelectedMoviesDao
) {

    suspend fun insertSelectedMovie(movie: SelectedMoviesEntity){
        return selectedMoviesDao.insertSelectedMovie(movie)
    }

    suspend fun getSelectedMovies(): List<SelectedMoviesEntity>{
        return selectedMoviesDao.getSelectedMovies()
    }

    suspend fun getSelectedMovie(id: String): SelectedMoviesEntity{
        return selectedMoviesDao.getSelectedMovie(id)
    }

    suspend fun deleteSelectedMovie(movie: SelectedMoviesEntity){
        return selectedMoviesDao.deleteSelectedMovie(movie)
    }
}
