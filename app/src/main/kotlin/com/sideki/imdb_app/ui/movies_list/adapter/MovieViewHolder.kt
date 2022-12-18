package com.sideki.imdb_app.ui.movies_list.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sideki.imdb_app.databinding.ItemMoviesBinding
import com.sideki.imdb_app.domain.model.MovieDataModel.MovieModel

class MovieViewHolder(
    private val binding: ItemMoviesBinding,
    val onMovieClick: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieModel) {
        with(binding) {
            title.text = movie.title
            imDbRating.text = movie.imDbRating
            image.load(movie.image)

            root.setOnClickListener {
                onMovieClick(movie.id)
            }
        }
    }
}
