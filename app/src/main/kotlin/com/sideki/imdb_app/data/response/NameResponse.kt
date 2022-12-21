package com.sideki.imdb_app.data.response


import com.google.gson.annotations.SerializedName

data class NameResponse(
    @SerializedName("awards") val awards: String?,
    @SerializedName("birthDate") val birthDate: String?,
    @SerializedName("castMovies") val castMovies: List<CastMovy?>?,
    @SerializedName("deathDate") val deathDate: Any?,
    @SerializedName("errorMessage") val errorMessage: String?,
    @SerializedName("height") val height: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("knownFor") val knownFor: List<KnownFor?>?,
    @SerializedName("name") val name: String?,
    @SerializedName("role") val role: String?,
    @SerializedName("summary") val summary: String?
){
    data class KnownFor(
        @SerializedName("fullTitle") val fullTitle: String?,
        @SerializedName("id") val id: String?,
        @SerializedName("image") val image: String?,
        @SerializedName("role") val role: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("year") val year: String?
    )

    data class CastMovy(
        @SerializedName("description") val description: String?,
        @SerializedName("id") val id: String?,
        @SerializedName("role") val role: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("year") val year: String?
    )
}
