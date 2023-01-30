package com.sideki.imdb_app.model.response

import com.google.gson.annotations.SerializedName

data class ActorResponse(
    @SerializedName("awards") val awards: String?,
    @SerializedName("birthDate") val birthDate: String?,
    @SerializedName("castMovies") val castMovies: List<CastMovie>?,
    @SerializedName("deathDate") val deathDate: String?,
    @SerializedName("errorMessage") val errorMessage: String?,
    @SerializedName("height") val height: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("knownFor") val knownFor: List<KnownFor>?,
    @SerializedName("name") val name: String?,
    @SerializedName("role") val role: String?,
    @SerializedName("summary") val summary: String?
) {
    data class KnownFor(
        @SerializedName("fullTitle") val fullTitle: String?,
        @SerializedName("id") val id: String?,
        @SerializedName("image") val image: String?,
        @SerializedName("role") val role: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("year") val year: String?
    )

    data class CastMovie(
        @SerializedName("description") val description: String?,
        @SerializedName("id") val id: String?,
        @SerializedName("role") val role: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("year") val year: String?
    )
}
