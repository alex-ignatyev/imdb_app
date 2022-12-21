package com.sideki.imdb_app.data.response

import com.google.gson.annotations.SerializedName
import com.sideki.imdb_app.data.response.MovieDataResponse.MovieResponse

data class Top250TVsResponse (
    @SerializedName("items") val movies: List<Top250TVs>,
    @SerializedName("errorMessage") val errorMessage: String?
){
    data class Top250TVs(
        @SerializedName("id") val id: String?,
        @SerializedName("rank") val rank: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("fullTitle") val fullTitle: String?,
        @SerializedName("year") val year: String?,
        @SerializedName("image") val image: String?,
        @SerializedName("crew") val crew: String?,
        @SerializedName("imDbRating") val imDbRating: String?,
        @SerializedName("imDbRatingCount") val imDbRatingCount: String?
    )
}
