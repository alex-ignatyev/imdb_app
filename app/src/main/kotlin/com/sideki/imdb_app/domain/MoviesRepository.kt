package com.sideki.imdb_app.domain

import com.sideki.imdb_app.data.api.ImdbApi
import com.sideki.imdb_app.db.MoviesDao
import com.sideki.imdb_app.db.entity.MovieEntity
import com.sideki.imdb_app.db.entity.MovieEntity.MovieType.COMING_SOON_MOVIES
import com.sideki.imdb_app.db.entity.MovieEntity.MovieType.MOST_POPULAR_MOVIES
import com.sideki.imdb_app.db.entity.MovieEntity.MovieType.TOP_250_MOVIES
import com.sideki.imdb_app.db.entity.MovieEntity.MovieType.TOP_250_TVS
import com.sideki.imdb_app.db.entity.toEntity
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesDao: MoviesDao,
    private val imdbApi: ImdbApi
) {

    suspend fun getMostPopularMovies(): List<MovieEntity> {
        val moviesFromDb = moviesDao.getMoviesByType(MOST_POPULAR_MOVIES)
        return if (moviesFromDb.isEmpty()) {
            val moviesFromInternet = imdbApi.getMostPopularMovies().movies.toEntity(MOST_POPULAR_MOVIES)
            moviesDao.insertMovies(moviesFromInternet)
            moviesFromInternet
        } else {
            moviesFromDb
        }
    }

    suspend fun getTop250Movies(): List<MovieEntity> {
        val moviesFromDb = moviesDao.getMoviesByType(TOP_250_MOVIES)
        return if (moviesFromDb.isEmpty()) {
            val moviesFromInternet = imdbApi.getTop250Movies().movies.toEntity(TOP_250_MOVIES)
            moviesDao.insertMovies(moviesFromInternet)
            moviesFromInternet
        } else {
            moviesFromDb
        }
    }

    suspend fun getTop250TVs(): List<MovieEntity> {
        val moviesFromDb = moviesDao.getMoviesByType(TOP_250_TVS)
        return if (moviesFromDb.isEmpty()) {
            val moviesFromInternet = imdbApi.getTop250TVs().movies.toEntity(TOP_250_TVS)
            moviesDao.insertMovies(moviesFromInternet)
            moviesFromInternet
        } else {
            moviesFromDb
        }
    }

    suspend fun getComingSoonMovies(): List<MovieEntity> {
        val moviesFromDb = moviesDao.getMoviesByType(COMING_SOON_MOVIES)
        return if (moviesFromDb.isEmpty()) {
            val moviesFromInternet = imdbApi.getComingSoonMovies().movies.toEntity(COMING_SOON_MOVIES)
            moviesDao.insertMovies(moviesFromInternet)
            moviesFromInternet
        } else {
            moviesFromDb
        }
    }
}
