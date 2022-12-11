package com.sideki.imdb_app.data.api

import com.sideki.imdb_app.data.response.MovieDataResponse
import retrofit2.http.GET

interface ImdbApi {

    @GET("MostPopularMovies/k_ekjecmoc")
    suspend fun getMovies(): MovieDataResponse
}