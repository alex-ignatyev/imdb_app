package com.example.imdb_app.ui.movies.adapter.screen

import com.example.imdb_app.databinding.ItemMoviesGroupBinding
import com.example.imdb_app.domain.model.MovieDataModel
import com.example.imdb_app.ui.movies.adapter.movie.MovieAdapter
import com.example.imdb_app.util.recycler.AdapterItem
import com.example.imdb_app.util.recycler.BaseViewHolder

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
