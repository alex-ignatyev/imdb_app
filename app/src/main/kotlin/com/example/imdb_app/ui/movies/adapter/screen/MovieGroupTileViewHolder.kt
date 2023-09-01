package com.example.imdb_app.ui.movies.adapter.screen

import com.example.imdb_app.databinding.ItemMovieGroupTitleBinding
import com.example.imdb_app.model.model.MoviesGroupTitleModel
import com.example.imdb_app.util.recycler.AdapterItem
import com.example.imdb_app.util.recycler.BaseViewHolder

class MovieGroupTileViewHolder(
    private val binding: ItemMovieGroupTitleBinding
) : BaseViewHolder(binding.root) {

    override fun bind(item: AdapterItem) {
        if(item !is MoviesGroupTitleModel) return
        binding.title.text = item.titleName
    }
}
