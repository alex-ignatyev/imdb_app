package com.sideki.imdb_app.domain.model

import com.sideki.imdb_app.data.response.MovieInfoResponse

data class MovieInfoModel(
    val title: String = "",
    val description: String = "",
    val rating: String = "",
    val imageUrl: String = ""
)

fun MovieInfoResponse.toDomain() = MovieInfoModel(
    title = title ?: "Название отсутствует",
    description = genres ?: "Жанр отсутствует",
    rating = imDbRating ?: "0.0",
    imageUrl = image ?: ""
)
