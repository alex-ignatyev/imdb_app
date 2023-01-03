package com.sideki.imdb_app.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey val id: String,
    val title: String,
    val image: String,
    val imDbRating: String
)
