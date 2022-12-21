package com.sideki.imdb_app.data.response

import com.google.gson.annotations.SerializedName
import com.sideki.imdb_app.data.response.Top250TVsResponse.Top250TVs

data class ComingSoonResponse(
    @SerializedName("items") val movies: List<ComingSoon>,
    @SerializedName("errorMessage") val errorMessage: String?
) {
    data class ComingSoon(
        @SerializedName("contentRating") val contentRating: Any?,
        @SerializedName("directorList") val directorList: List<Any?>?,
        @SerializedName("directors") val directors: Any?,
        @SerializedName("fullTitle") val fullTitle: String?,
        @SerializedName("genreList") val genreList: List<Genre?>?,
        @SerializedName("genres") val genres: String?,
        @SerializedName("id") val id: String?,
        @SerializedName("imDbRating") val imDbRating: Any?,
        @SerializedName("imDbRatingCount") val imDbRatingCount: Any?,
        @SerializedName("image") val image: String?,
        @SerializedName("metacriticRating") val metacriticRating: Any?,
        @SerializedName("plot") val plot: Any?,
        @SerializedName("releaseState") val releaseState: String?,
        @SerializedName("runtimeMins") val runtimeMins: Any?,
        @SerializedName("runtimeStr") val runtimeStr: Any?,
        @SerializedName("starList") val starList: List<Star?>?,
        @SerializedName("stars") val stars: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("year") val year: String?
    )

    data class Star(
        @SerializedName("id") val id: Any?,
        @SerializedName("name") val name: String?
    )

    data class Genre(
        @SerializedName("key") val key: String?,
        @SerializedName("value") val value: String?
    )
}
