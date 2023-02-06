package com.sideki.imdb_app.data.api

import com.sideki.imdb_app.model.response.ActorResponse
import com.sideki.imdb_app.model.response.MetacriticReviewsResponse
import com.sideki.imdb_app.model.response.MovieDataResponse
import com.sideki.imdb_app.model.response.MovieInfoResponse
import com.sideki.imdb_app.model.response.ReviewsResponse
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

    /** Movies Lists */

    @GET("MostPopularMovies/{api_key}")
    suspend fun getMostPopularMovies(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_zu9ilkcn"
    ): MovieDataResponse

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

    /** Movie Info */

    @GET("Title/{api_key}/{title_id}/Images,Trailer,Ratings")
    suspend fun getMovieInfo(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_zu9ilkcn",
        @Path(value = "title_id", encoded = true) movieId: String = "tt1375666"
    ): MovieInfoResponse

    @GET("Name/{api_key}/{name_id}")
    suspend fun getActorInfo(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_zu9ilkcn",
        @Path(value = "name_id", encoded = true) actorId: String = "nm0000154"
    ): ActorResponse

    @GET("Reviews/{api_key}/{title_id}")
    suspend fun getReviews(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_zu9ilkcn",
        @Path(value = "reviews_id", encoded = true) reviewsId: String = "tt1375666"
    ): ReviewsResponse

    @GET("MetacriticReviews/{api_key}/{title_id}")
    suspend fun getMetacriticReviews(
        @Path(value = "api_key", encoded = true) apiKey: String = "k_zu9ilkcn",
        @Path(value = "metacriticReviews_id", encoded = true) metacriticReviewsId: String = "tt1375666"
    ): MetacriticReviewsResponse
}
