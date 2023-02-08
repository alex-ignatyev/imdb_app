package com.sideki.imdb_app.domain

import com.sideki.imdb_app.data.api.ImdbApi
import com.sideki.imdb_app.data.response.MovieDataResponse
import com.sideki.imdb_app.db.DataStorePreferenceStorage
import com.sideki.imdb_app.db.MoviesDao
import com.sideki.imdb_app.db.entity.MovieEntity
import com.sideki.imdb_app.db.entity.MovieEntity.MovieType
import com.sideki.imdb_app.db.entity.MovieEntity.MovieType.COMING_SOON_MOVIES
import com.sideki.imdb_app.db.entity.MovieEntity.MovieType.MOST_POPULAR_MOVIES
import com.sideki.imdb_app.db.entity.MovieEntity.MovieType.TOP_250_MOVIES
import com.sideki.imdb_app.db.entity.MovieEntity.MovieType.TOP_250_TVS
import com.sideki.imdb_app.db.entity.toEntity
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class MoviesRepository @Inject constructor(
    private val moviesDao: MoviesDao,
    private val imdbApi: ImdbApi,
    private val prefs: DataStorePreferenceStorage
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
        val currentDate = getLocalDate()
        val dateFromPreferences = prefs.date
        if (currentDate != dateFromPreferences) {
            moviesDao.clearAllMovies()
            prefs.saveDate(currentDate.toString())
        }
    }

    private fun getLocalDate(): Flow<String> = callbackFlow {
        LocalDate.now().toString()
    }

    private suspend inline fun getMoviesByType(type: MovieType, request: () -> MovieDataResponse): List<MovieEntity> {
        val moviesFromDb = moviesDao.getMoviesByType(type)
        return if (moviesFromDb.isEmpty()) {
            val moviesFromInternet = request.invoke().movies.toEntity(type)
            moviesDao.insertMovies(moviesFromInternet)
            moviesFromInternet
        } else {
            moviesFromDb
        }
    }
}
