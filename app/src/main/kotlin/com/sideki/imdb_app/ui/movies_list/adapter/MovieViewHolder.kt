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
            title.text = movie.title
            imDbRating.text = movie.imDbRating
            image.load(movie.image)

            root.setOnClickListener {
                onMovieClick(movie.id.orEmpty())
            }
        }
    }
}
