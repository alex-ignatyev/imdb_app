package com.sideki.imdb_app.ui.movies_list.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sideki.imdb_app.data.response.MovieResponse
import com.sideki.imdb_app.databinding.ItemMoviesBinding

class MovieViewHolder(
    private val binding: ItemMoviesBinding,
    val onMovieClick: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieResponse) {
        with(binding) {
            image.load(movie.image)
            imDbRating.text = movie.imDbRating
            title.text = movie.title

            root.setOnClickListener {
                onMovieClick(movie.id.orEmpty())
            }
        }
    }
}
