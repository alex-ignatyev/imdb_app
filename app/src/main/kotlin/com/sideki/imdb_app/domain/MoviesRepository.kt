package com.sideki.imdb_app.domain

import com.sideki.imdb_app.data.api.ImdbApi
import com.sideki.imdb_app.data.response.toEntity
import com.sideki.imdb_app.db.MoviesDao
import com.sideki.imdb_app.db.entity.MovieEntity
import com.sideki.imdb_app.db.entity.MovieType.COMING_SOON_MOVIES
import com.sideki.imdb_app.db.entity.MovieType.MOST_POPULAR_MOVIES
import com.sideki.imdb_app.db.entity.MovieType.TOP_250_MOVIES
import com.sideki.imdb_app.db.entity.MovieType.TOP_250_TVS
import com.sideki.imdb_app.di.DataStorePreferences
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesDao: MoviesDao,
    private val imdbApi: ImdbApi
) {

    suspend fun getMostPopularMovies(): List<MovieEntity> {
        val moviesFromDb = moviesDao.getMovies(MOST_POPULAR_MOVIES)
        return if (moviesFromDb.isEmpty()) {
            val moviesFromInternet = imdbApi.getMostPopularMovies().movies.toEntity(MOST_POPULAR_MOVIES)
            moviesDao.insert(moviesFromInternet)
            moviesFromInternet
        } else {
            moviesFromDb
        }
    }

    suspend fun getTop250Movies(): List<MovieEntity> {
        val moviesFromDb = moviesDao.getMovies(TOP_250_MOVIES)
        return if (moviesFromDb.isEmpty()) {
            val moviesFromInternet = imdbApi.getTop250Movies().movies.toEntity(TOP_250_MOVIES)
            moviesDao.insert(moviesFromInternet)
            moviesFromInternet
        } else {
            moviesFromDb
        }
    }

    suspend fun getTop250TVs(): List<MovieEntity> {
        val moviesFromDb = moviesDao.getMovies(TOP_250_TVS)
        return if (moviesFromDb.isEmpty()) {
            val moviesFromInternet = imdbApi.getTop250TVs().movies.toEntity(TOP_250_TVS)
            moviesDao.insert(moviesFromInternet)
            moviesFromInternet
        } else {
            moviesFromDb
        }
    }

    suspend fun getComingSoonMovies(): List<MovieEntity> {
        val moviesFromDb = moviesDao.getMovies(COMING_SOON_MOVIES)
        return if (moviesFromDb.isEmpty()) {
            val moviesFromInternet = imdbApi.getComingSoonMovies().movies.toEntity(COMING_SOON_MOVIES)
            moviesDao.insert(moviesFromInternet)
            moviesFromInternet
        } else {
            moviesFromDb
        }
    }
}
