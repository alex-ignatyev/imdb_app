package com.sideki.imdb_app.ui.movies_list.adapter.movie

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sideki.imdb_app.databinding.ItemMovieBinding
import com.sideki.imdb_app.model.model.MovieDataModel.MovieModel
import com.sideki.imdb_app.util.recycler.BaseViewHolder
import com.sideki.imdb_app.util.recycler.viewBinding

class MovieAdapter(
    private val onMovieClick: (String) -> Unit
) : ListAdapter<MovieModel, BaseViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MovieViewHolder(parent.viewBinding(ItemMovieBinding::inflate), onMovieClick)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object Differ : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel) = oldItem.id == newItem.id
    }
}
