package com.sideki.imdb_app.ui.movie_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sideki.imdb_app.ui.movie_info.view.MovieInfoScreen
import com.sideki.imdb_app.ui.theme.Imdb_appTheme
import com.sideki.imdb_app.util.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieInfoFragment : Fragment() {

    private val vm by viewModels<MovieInfoVM>()
    private val args by navArgs<MovieInfoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ) = setContent {
        vm.getMovieInfo(args.movieId)
        Imdb_appTheme {
            MovieInfoScreen(
                movie = vm.data,
                onBackClick = { findNavController().popBackStack() }
            )
        }
    }
}