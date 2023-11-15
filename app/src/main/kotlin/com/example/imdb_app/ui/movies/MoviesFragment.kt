package com.example.imdb_app.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.imdb_app.R
import com.example.imdb_app.databinding.FragmentMoviesBinding
import com.example.imdb_app.ui.movies.adapter.screen.ScreenAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val vm by viewModels<MoviesVM>()
    private val adapter = ScreenAdapter {
        findNavController().navigate(MoviesFragmentDirections.toMovieInfoFragment(it))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMoviesBinding.bind(view)
        binding.screenRv.adapter = adapter
        vm.movies.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}

