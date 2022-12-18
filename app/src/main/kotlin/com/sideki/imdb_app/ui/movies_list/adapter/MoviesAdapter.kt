package com.sideki.imdb_app.ui.movies_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sideki.imdb_app.databinding.ItemMoviesBinding
import com.sideki.imdb_app.domain.model.MovieDataModel
import com.sideki.imdb_app.domain.model.MovieDataModel.MovieModel

class MoviesAdapter(
    private val onMovieClick: (String) -> Unit
) : ListAdapter<MovieModel, MovieViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, onMovieClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object Differ : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel) = oldItem == newItem
    }
}
