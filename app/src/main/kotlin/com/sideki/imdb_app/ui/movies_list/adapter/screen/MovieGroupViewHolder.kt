package com.sideki.imdb_app.ui.movies_list.adapter.screen

import com.sideki.imdb_app.databinding.ItemMoviesGroupBinding
import com.sideki.imdb_app.domain.model.MovieDataModel
import com.sideki.imdb_app.ui.movies_list.adapter.movie.MovieAdapter
import com.sideki.imdb_app.util.recycler.AdapterItem
import com.sideki.imdb_app.util.recycler.BaseViewHolder

class MovieGroupViewHolder(
    private val binding: ItemMoviesGroupBinding,
    private val onMovieClick: (String) -> Unit
) : BaseViewHolder(binding.root) {

    private val adapter = MovieAdapter(onMovieClick)

    override fun bind(item: AdapterItem) {
        if (item !is MovieDataModel) return
        binding.moviesGroupRv.adapter = adapter
        adapter.submitList(item.movies)
    }
}
