package com.example.imdb_app.ui.movies.adapter.movie

import coil.load
import com.example.imdb_app.databinding.ItemMovieBinding
import com.example.imdb_app.domain.model.MovieDataModel.MovieModel
import com.example.imdb_app.util.recycler.AdapterItem
import com.example.imdb_app.util.recycler.BaseViewHolder

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
