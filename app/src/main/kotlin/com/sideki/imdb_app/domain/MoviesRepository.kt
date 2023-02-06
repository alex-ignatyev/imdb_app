package com.sideki.imdb_app.domain

import com.sideki.imdb_app.data.DataStorePref
import com.sideki.imdb_app.data.api.ImdbApi
import com.sideki.imdb_app.data.db.MoviesDao
import com.sideki.imdb_app.model.entity.MovieEntity
import com.sideki.imdb_app.model.entity.MovieEntity.MovieType
import com.sideki.imdb_app.model.entity.MovieEntity.MovieType.COMING_SOON_MOVIES
import com.sideki.imdb_app.model.entity.MovieEntity.MovieType.MOST_POPULAR_MOVIES
import com.sideki.imdb_app.model.entity.MovieEntity.MovieType.TOP_250_MOVIES
import com.sideki.imdb_app.model.entity.MovieEntity.MovieType.TOP_250_TVS
import com.sideki.imdb_app.model.entity.toEntity
import com.sideki.imdb_app.model.response.MovieDataResponse
import java.time.LocalDate
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesDao: MoviesDao,
    private val imdbApi: ImdbApi,
    private val store: DataStorePref
) {

    suspend fun getMostPopularMovies(): List<MovieEntity> {
        return getMoviesByType(MOST_POPULAR_MOVIES) {
            imdbApi.getMostPopularMovies()
        }
    }

    suspend fun getTop250Movies(): List<MovieEntity> {
        return getMoviesByType(TOP_250_MOVIES) {
            imdbApi.getTop250Movies()
        }
    }

    suspend fun getTop250TVs(): List<MovieEntity> {
        return getMoviesByType(TOP_250_TVS) {
            imdbApi.getTop250TVs()
        }
    }

    suspend fun getComingSoonMovies(): List<MovieEntity> {
        return getMoviesByType(COMING_SOON_MOVIES) {
            imdbApi.getComingSoonMovies()
        }
    }

    suspend fun clearAllMovies() {
        val today = LocalDate.now().toString()
        val prefDate = store.getDate()
        if (today != prefDate) {
            moviesDao.clearAllMovies()
            store.saveDate(today)
        }
    }

    private suspend inline fun getMoviesByType(type: MovieType, request: () -> MovieDataResponse): List<MovieEntity> {
        val moviesFromDb = moviesDao.getMoviesByType(type)
        return moviesFromDb.ifEmpty {
            val moviesFromInternet = request.invoke().movies.toEntity(type)
            moviesDao.insertMovies(moviesFromInternet)
            moviesFromInternet
        }
    }
}
