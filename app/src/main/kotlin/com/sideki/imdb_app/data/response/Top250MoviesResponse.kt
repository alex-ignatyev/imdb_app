package com.sideki.imdb_app.data.response

import com.google.gson.annotations.SerializedName

data class Top250MoviesResponse(
    @SerializedName("items") val movies: List<Top250Movies>,
    @SerializedName("errorMessage") val errorMessage: String?
) {
    data class Top250Movies(
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
