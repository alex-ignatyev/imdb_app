package com.example.imdb_app.data.repository

import com.example.imdb_app.data.api.ImdbApi
import com.example.imdb_app.data.db.DataStorePreferenceStorage
import com.example.imdb_app.data.db.MoviesDao
import com.example.imdb_app.data.db.entity.MovieEntity
import com.example.imdb_app.data.db.entity.MovieEntity.MovieType
import com.example.imdb_app.data.db.entity.MovieEntity.MovieType.COMING_SOON_MOVIES
import com.example.imdb_app.data.db.entity.MovieEntity.MovieType.MOST_POPULAR_MOVIES
import com.example.imdb_app.data.db.entity.MovieEntity.MovieType.TOP_250_MOVIES
import com.example.imdb_app.data.db.entity.MovieEntity.MovieType.TOP_250_TVS
import com.example.imdb_app.data.db.entity.toEntity
import com.example.imdb_app.data.response.MovieDataResponse
import com.example.imdb_app.domain.model.MovieDataModel.MovieModel
import com.example.imdb_app.domain.model.toModel
import com.example.imdb_app.domain.repository.MoviesRepository
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class MoviesRepositoryImpl @Inject constructor(
    private val moviesDao: MoviesDao,
    private val imdbApi: ImdbApi,
    private val prefs: DataStorePreferenceStorage
) : MoviesRepository {

    override suspend fun getMostPopularMovies(): List<MovieModel> {
        return getMoviesByType(MOST_POPULAR_MOVIES) {
            imdbApi.getMostPopularMovies()
        }.toModel()
    }

    override suspend fun getTop250Movies(): List<MovieModel> {
        return getMoviesByType(TOP_250_MOVIES) { imdbApi.getTop250Movies() }.toModel()
    }

    override suspend fun getTop250TVs(): List<MovieModel> {
        return getMoviesByType(TOP_250_TVS) {
            imdbApi.getTop250TVs()
        }.toModel()
    }

    override suspend fun getComingSoonMovies(): List<MovieModel> {
        return getMoviesByType(COMING_SOON_MOVIES) {
            imdbApi.getComingSoonMovies()
        }.toModel()
    }


    override suspend fun clearAllMovies() {
        val today = getLocalDate()
        val prefDate = prefs.date
        if (today != prefDate) {
            moviesDao.clearAllMovies()
            prefs.saveDate(today.toString())
        }
    }

    private fun getLocalDate(): Flow<String> = callbackFlow {
        LocalDate.now()
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
