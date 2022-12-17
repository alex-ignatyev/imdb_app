package com.sideki.imdb_app.data.api

import com.sideki.imdb_app.data.response.MovieDataResponse
import com.sideki.imdb_app.data.response.MovieInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ImdbApi {

    @GET("MostPopularMovies/{api_key}")
    suspend fun getMovies(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_ekjecmoc"
    ): MovieDataResponse

    @GET("Title/{api_key}/{title_id}")
    suspend fun getMovieInfo(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_ekjecmoc",
        @Path(value = "title_id", encoded = true) titleId: String = "tt1375666"
    ): MovieInfoResponse
}
