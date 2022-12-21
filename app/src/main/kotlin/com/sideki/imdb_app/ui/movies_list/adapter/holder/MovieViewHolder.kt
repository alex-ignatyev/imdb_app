package com.sideki.imdb_app.ui.movies_list.adapter.holder

import coil.load
import com.sideki.imdb_app.databinding.ItemMoviesBinding
import com.sideki.imdb_app.domain.model.MovieDataModel.MovieModel
import com.sideki.imdb_app.util.recycler.AdapterItem
import com.sideki.imdb_app.util.recycler.BaseViewHolder

class MovieViewHolder(
    private val binding: ItemMoviesBinding,
    val onMovieClick: (String) -> Unit
) : BaseViewHolder(binding.root) {

    override fun bind(item: AdapterItem) {
        if(item !is MovieModel) return
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
