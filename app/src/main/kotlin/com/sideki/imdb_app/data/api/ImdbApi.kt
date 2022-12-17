package com.sideki.imdb_app.data.api

import com.sideki.imdb_app.data.response.MovieDataResponse
import com.sideki.imdb_app.data.response.MovieInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

//k_zu9ilkcn
//k_ekjecmoc

//https://imdb-api.com/en/API/Top250Movies/k_zu9ilkcn
//https://imdb-api.com/en/API/Top250TVs/k_zu9ilkcn
//https://imdb-api.com/en/API/MostPopularMovies/k_zu9ilkcn
//https://imdb-api.com/en/API/MostPopularTVs/k_zu9ilkcn
//https://imdb-api.com/en/API/ComingSoon/k_zu9ilkcn
//https://imdb-api.com/en/API/Name/k_zu9ilkcn/nm0000154 // инфа о актере

interface ImdbApi {

    @GET("MostPopularMovies/{api_key}")
    suspend fun getMovies(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_zu9ilkcn"
    ): MovieDataResponse



    @GET("Title/{api_key}/{title_id}")
    suspend fun getMovieInfo(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_zu9ilkcn",
        @Path(value = "title_id", encoded = true) titleId: String = "tt1375666"
    ): MovieInfoResponse
}