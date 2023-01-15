package com.sideki.imdb_app.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sideki.imdb_app.data.response.MovieDataResponse.MovieResponse

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey val id: String,
    val title: String,
    val image: String,
    val imDbRating: String
)

fun List<MovieResponse>.toEntity() = this.map { response ->
    MovieEntity(
        id = response.id.orEmpty(),
        title = response.title ?: "Без названия",
        image = response.image ?: "",
        imDbRating = response.imDbRating ?: "0.0"
    )
}
