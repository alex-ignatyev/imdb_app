package com.sideki.imdb_app.ui.movies_list.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sideki.imdb_app.data.response.MovieResponse
import com.sideki.imdb_app.databinding.ItemMoviesBinding

class MovieViewHolder(
    private val binding: ItemMoviesBinding,
    val onMovieClick: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movies: MovieResponse) {
        binding.apply {
            image.load(movies.image)
            imDbRating.text = movies.imDbRating
            title.text = movies.title

            root.setOnClickListener {
                onMovieClick(movie.id.orEmpty())
            }
        }
    }
}
