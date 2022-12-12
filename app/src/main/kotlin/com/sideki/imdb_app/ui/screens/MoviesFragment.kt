package com.sideki.imdb_app.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sideki.imdb_app.R
import com.sideki.imdb_app.databinding.FragmentMoviesBinding
import com.sideki.imdb_app.ui.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesFragment: Fragment(R.layout.fragment_movies) {

    //val viewModel: MoviesFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*
       val binding = FragmentMoviesBinding.bind(view)

        val moviesAdapter = MoviesAdapter()

        binding.apply {
            recyclerViewMovies.apply {
                adapter = moviesAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
        viewModel.movies.observe(viewLifecycleOwner, Observer{
            moviesAdapter.submitList(it)
        })*/
    }
}