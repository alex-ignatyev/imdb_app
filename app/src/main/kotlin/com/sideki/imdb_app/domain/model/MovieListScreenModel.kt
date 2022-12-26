package com.sideki.imdb_app.domain.model

import com.sideki.imdb_app.domain.model.MovieDataModel.MovieModel
import com.sideki.imdb_app.util.recycler.AdapterItem
import java.util.UUID

data class MovieListScreenModel(
    override var id: String = UUID.randomUUID().toString(),
    val title: MoviesGroupTitleModel,
    val movieList: List<MovieModel>
): AdapterItem()
