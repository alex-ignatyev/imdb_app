package com.sideki.imdb_app.data.api

import com.sideki.imdb_app.data.response.MetacriticReviewsResponse
import com.sideki.imdb_app.data.response.MovieDataResponse
import com.sideki.imdb_app.data.response.MovieInfoResponse
import com.sideki.imdb_app.data.response.ActorResponse
import com.sideki.imdb_app.data.response.ReviewsResponse
import retrofit2.http.GET
import retrofit2.http.Path

//k_zu9ilkcn
//k_ekjecmoc

//https://imdb-api.com/en/API/Top250Movies/k_zu9ilkcn
//https://imdb-api.com/en/API/Top250TVs/k_zu9ilkcn
//https://imdb-api.com/en/API/MostPopularTVs/k_zu9ilkcn
//https://imdb-api.com/en/API/ComingSoon/k_zu9ilkcn
//https://imdb-api.com/en/API/Name/k_zu9ilkcn/nm0000154 // инфа о актере
//https://imdb-api.com/ru/API/Reviews/k_zu9ilkcn/tt1375666
//https://imdb-api.com/ru/API/MetacriticReviews/k_zu9ilkcn/tt1375666

interface ImdbApi {

    @GET("MostPopularMovies/{api_key}")
    suspend fun getMovies(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_zu9ilkcn"
    ): MovieDataResponse

    @GET("Title/{api_key}/{title_id}/Images,Trailer,Ratings")
    suspend fun getMovieInfo(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_zu9ilkcn",
        @Path(value = "title_id", encoded = true) titleId: String = "tt1375666"
    ): MovieInfoResponse

    @GET("Top250Movies/{api_key}")
    suspend fun getTop250Movies(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_zu9ilkcn"
    ): MovieDataResponse

    @GET("Top250TVs/{api_key}")
    suspend fun getTop250TVs(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_zu9ilkcn"
    ): MovieDataResponse

    @GET("ComingSoon/{api_key}")
    suspend fun getComingSoonMovies(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_zu9ilkcn"
    ): MovieDataResponse

    @GET("Name/{api_key}/{name_id}")
    suspend fun getActor(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_zu9ilkcn",
        @Path(value = "name_id", encoded = true) titleId: String = "nm0000154"
    ): ActorResponse

    @GET("Reviews/{api_key}/{title_id}")
    suspend fun getReviews(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_zu9ilkcn",
        @Path(value = "reviews_id", encoded = true) titleId: String = "tt1375666"
    ): ReviewsResponse

    @GET("MetacriticReviews/{api_key}/{title_id}")
    suspend fun getMetacriticReviews(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_zu9ilkcn",
        @Path(value = "metacriticReviews_id", encoded = true) titleId: String = "tt1375666"
    ): MetacriticReviewsResponse
}
