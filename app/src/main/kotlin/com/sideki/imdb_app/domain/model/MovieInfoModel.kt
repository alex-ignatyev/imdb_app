package com.sideki.imdb_app.domain.model

import com.sideki.imdb_app.data.response.MovieInfoResponse

data class MovieInfoModel(
    val title: String
)

fun MovieInfoResponse.toDomain() = MovieInfoModel(
    title = title ?: "Название отсутствует"
)
