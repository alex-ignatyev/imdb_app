package com.sideki.imdb_app.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sideki.imdb_app.data.response.MovieDataResponse.MovieResponse
import com.sideki.imdb_app.db.entity.MovieEntity.MovieType

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey val id: String,
    val title: String,
    val image: String,
    val imDbRating: String,
    val type: MovieType
) {
    enum class MovieType {
        MOST_POPULAR_MOVIES,
        TOP_250_MOVIES,
        TOP_250_TVS,
        COMING_SOON_MOVIES;
    }
}

fun List<MovieResponse>.toEntity(type: MovieType) = this.map { response ->
    MovieEntity(
        id = response.id.orEmpty(),
        title = response.title ?: "Без названия",
        image = response.image ?: "",
        imDbRating = response.imDbRating ?: "0.0",
        type = type
    )
}
