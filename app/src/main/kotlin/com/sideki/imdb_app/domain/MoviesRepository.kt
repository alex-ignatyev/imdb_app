package com.sideki.imdb_app.domain

import com.sideki.imdb_app.data.api.ImdbApi
import com.sideki.imdb_app.db.MoviesDao
import com.sideki.imdb_app.db.entity.MovieEntity
import com.sideki.imdb_app.db.entity.toEntity
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesDao: MoviesDao,
    private val imdbApi: ImdbApi
) {
    suspend fun getMovies(): List<MovieEntity> {
        val moviesFromDb = moviesDao.getMostPopularMovies()
        return if (moviesFromDb.isEmpty()){
            val moviesFromInternet = imdbApi.getMovies().movies.toEntity()
            moviesDao.insertMostPopularMovies(moviesFromInternet)
            moviesFromInternet
        } else{
            moviesFromDb
        }
    }
}
