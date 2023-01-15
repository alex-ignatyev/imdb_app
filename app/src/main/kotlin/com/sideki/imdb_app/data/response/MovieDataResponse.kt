package com.sideki.imdb_app.data.response

import com.google.gson.annotations.SerializedName
import com.sideki.imdb_app.data.response.MovieDataResponse.MovieResponse
import com.sideki.imdb_app.db.entity.MovieEntity

data class MovieDataResponse(
    @SerializedName("items") val movies: List<MovieResponse>,
    @SerializedName("errorMessage") val errorMessage: String?
) {
    data class MovieResponse(
        @SerializedName("crew") val crew: String?,
        @SerializedName("fullTitle") val fullTitle: String?,
        @SerializedName("id") val id: String?,
        @SerializedName("imDbRating") val imDbRating: String?,
        @SerializedName("imDbRatingCount") val imDbRatingCount: String?,
        @SerializedName("image") val image: String?,
        @SerializedName("rank") val rank: String?,
        @SerializedName("rankUpDown") val rankUpDown: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("year") val year: String?
    )
}
