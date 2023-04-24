package com.sideki.imdb_app.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sideki.imdb_app.model.entity.MovieEntity.MovieType
import com.sideki.imdb_app.model.response.MovieDataResponse.MovieResponse

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey val id: String,
    val title: String,
    val image: String,
    val imDbRating: String,
    val type: MovieType
) {

    enum class MovieType(val title: String) {
        MOST_POPULAR_MOVIES("Most popular movies"),
        TOP_250_MOVIES("Top 250 movies"),
        TOP_250_TVS("Top 250 TVs"),
        COMING_SOON_MOVIES("Coming soon");
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
}.filter { it.id.isNotBlank() }
