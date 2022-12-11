package com.sideki.imdb_app.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sideki.imdb_app.data.response.MovieResponse
import com.sideki.imdb_app.databinding.ItemMoviesBinding


class MoviesAdapter : ListAdapter<MovieResponse, MoviesAdapter.MovieViewHolder>(DiffCallback()) {

    class MovieViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movies: MovieResponse) {
            binding.apply {
                textViewMovieTitle.text = movies.title
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallback: DiffUtil.ItemCallback<MovieResponse>(){
        override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
           return oldItem == newItem
        }

    }
}