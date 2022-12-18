package com.sideki.imdb_app.domain.model

import com.sideki.imdb_app.data.response.MovieDataResponse
import com.sideki.imdb_app.data.response.MovieDataResponse.MovieResponse
import com.sideki.imdb_app.domain.model.MovieDataModel.MovieModel

data class MovieDataModel(
    val movies: List<MovieModel> = emptyList()
) {
    data class MovieModel(
        val id: String = "",
        val title: String = "Без названия",
        val imDbRating: String = "0.0",
        val image: String = ""
    )
}

fun MovieDataResponse.toDomain() = MovieDataModel(
    movies = movies.map { movie ->
        MovieModel(
            id = movie.id.orEmpty(),
            title = movie.title ?: "Без названия",
            imDbRating = movie.imDbRating ?: "0.0",
            image = movie.image ?: ""
        )
    }
)
