package com.sideki.imdb_app.ui.movies_list

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sideki.imdb_app.R
import com.sideki.imdb_app.databinding.FragmentMoviesBinding
import com.sideki.imdb_app.ui.movies_list.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val vm by viewModels<MoviesVM>()
    private val adapter = MoviesAdapter {
        findNavController().navigate(MoviesFragmentDirections.toMovieInfoFragment(it))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMoviesBinding.bind(view)
        binding.movies.adapter = adapter
        vm.movies.observe(viewLifecycleOwner) {
            adapter.submitList(it.movies)
        }
    }
}
