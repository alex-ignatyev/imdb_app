package com.sideki.imdb_app.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sideki.imdb_app.data.response.MovieInfoResponse
import com.sideki.imdb_app.domain.model.MovieInfoModel

@Entity(tableName = "selected_movies_table")
data class SelectedMoviesEntity(
    @PrimaryKey val id: String,
    val title: String,
    val image: String
)

fun MovieInfoResponse.toEntity() = SelectedMoviesEntity(
    id = id ?: "",
    title = title ?: "",
    image = image ?: ""
)
