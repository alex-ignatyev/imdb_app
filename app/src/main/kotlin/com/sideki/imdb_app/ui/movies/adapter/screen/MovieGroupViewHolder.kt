package com.sideki.imdb_app.ui.movies.adapter.screen

import com.sideki.imdb_app.databinding.ItemMoviesGroupBinding
import com.sideki.imdb_app.model.model.MovieDataModel
import com.sideki.imdb_app.ui.movies.adapter.movie.MovieAdapter
import com.sideki.imdb_app.util.recycler.AdapterItem
import com.sideki.imdb_app.util.recycler.BaseViewHolder

class MovieGroupViewHolder(
    private val binding: ItemMoviesGroupBinding,
    onMovieClick: (String) -> Unit
) : BaseViewHolder(binding.root) {

    private val adapter = MovieAdapter(onMovieClick)

    override fun bind(item: AdapterItem) {
        if (item !is MovieDataModel) return
        if (binding.moviesGroupRv.adapter == null){
            binding.moviesGroupRv.adapter = adapter
        }
        adapter.submitList(item.movies)
    }
}
