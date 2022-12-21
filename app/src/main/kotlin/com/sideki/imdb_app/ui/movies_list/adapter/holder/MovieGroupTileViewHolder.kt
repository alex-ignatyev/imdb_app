package com.sideki.imdb_app.ui.movies_list.adapter.holder

import com.sideki.imdb_app.databinding.ItemMovieGroupTitleBinding
import com.sideki.imdb_app.domain.model.MoviesGroupTitleModel
import com.sideki.imdb_app.util.recycler.AdapterItem
import com.sideki.imdb_app.util.recycler.BaseViewHolder

class MovieGroupTileViewHolder(
    private val binding: ItemMovieGroupTitleBinding
) : BaseViewHolder(binding.root) {

    override fun bind(item: AdapterItem) {
        if(item !is MoviesGroupTitleModel) return
        binding.title.text = item.titleName
    }
}
