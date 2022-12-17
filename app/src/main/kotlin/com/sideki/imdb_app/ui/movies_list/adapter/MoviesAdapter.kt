package com.sideki.imdb_app.ui.movies_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sideki.imdb_app.data.response.MovieResponse
import com.sideki.imdb_app.databinding.ItemMoviesBinding

class MoviesAdapter(
    private val onMovieClick: (String) -> Unit
) : ListAdapter<MovieResponse, MovieViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, onMovieClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object Differ : DiffUtil.ItemCallback<MovieResponse>() {
        override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse) = oldItem == newItem
    }
}
