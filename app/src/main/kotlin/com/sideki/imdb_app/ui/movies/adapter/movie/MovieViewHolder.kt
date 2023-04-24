package com.sideki.imdb_app.ui.movies.adapter.movie

import coil.load
import com.sideki.imdb_app.databinding.ItemMovieBinding
import com.sideki.imdb_app.model.model.MovieDataModel.MovieModel
import com.sideki.imdb_app.util.recycler.AdapterItem
import com.sideki.imdb_app.util.recycler.BaseViewHolder

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    val onMovieClick: (String) -> Unit
) : BaseViewHolder(binding.root) {

    override fun bind(item: AdapterItem) {
        if (item !is MovieModel) return
        with(binding) {
            title.text = item.title
            imDbRating.text = item.imDbRating
            image.load(item.image)

            root.setOnClickListener {
                onMovieClick(item.id)
            }
        }
    }
}
