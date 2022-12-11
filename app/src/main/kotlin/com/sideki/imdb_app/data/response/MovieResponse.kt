package com.sideki.imdb_app.data.response


import com.google.gson.annotations.SerializedName

data class MovieDataResponse(
    @SerializedName("items") val movies: List<MovieResponse>,
    @SerializedName("errorMessage") val errorMessage: String?
)