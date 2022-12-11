package com.sideki.imdb_app.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sideki.imdb_app.R
import com.sideki.imdb_app.databinding.FragmentMoviesBinding
import com.sideki.imdb_app.ui.MoviesAdapter

class MoviesFragment: Fragment(R.layout.fragment_movies) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMoviesBinding.bind(view)

        val moviesAdapter = MoviesAdapter()

        binding.apply {
            recyclerViewMovies.apply {
                adapter = moviesAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }
}