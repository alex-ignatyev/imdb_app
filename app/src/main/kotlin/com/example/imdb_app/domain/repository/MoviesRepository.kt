package com.example.imdb_app.domain.repository

import com.example.imdb_app.domain.model.MovieDataModel.MovieModel

interface MoviesRepository {

    suspend fun getMostPopularMovies(): List<MovieModel>

    suspend fun getTop250Movies(): List<MovieModel>

    suspend fun getTop250TVs(): List<MovieModel>

    suspend fun getComingSoonMovies(): List<MovieModel>

    suspend fun clearAllMovies()
}
