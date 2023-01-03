package com.sideki.imdb_app.domain.model

import com.sideki.imdb_app.data.response.MovieDataResponse
import com.sideki.imdb_app.db.entity.MovieEntity
import com.sideki.imdb_app.domain.model.MovieDataModel.MovieModel
import com.sideki.imdb_app.util.recycler.AdapterItem
import java.util.UUID

data class MovieDataModel(
    override var id: String = UUID.randomUUID().toString(),
    val movies: List<MovieModel> = emptyList()
) : AdapterItem() {
    data class MovieModel(
        override var id: String = "",
        val title: String = "Без названия",
        val imDbRating: String = "0.0",
        val image: String = ""
    ) : AdapterItem()
}

fun List<MovieEntity>.toModel() = this.map {
    MovieModel(
        id = it.id,
        title = it.title,
        imDbRating = it.imDbRating,
        image = it.image
    )
}
