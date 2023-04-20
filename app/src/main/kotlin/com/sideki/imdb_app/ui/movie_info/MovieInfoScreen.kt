package com.sideki.imdb_app.ui.movie_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sideki.imdb_app.data.api.ImdbApi
import com.sideki.imdb_app.ui.movie_info.MovieInfoEffect.OpenMoviesScreen
import com.sideki.imdb_app.ui.movie_info.view.MovieInfoView
import com.sideki.imdb_app.util.base.ViewModel
import com.sideki.imdb_app.util.setContent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieInfoScreen : Fragment() {

    private val vm by viewModels<MovieInfoVM>()
    private val args by navArgs<MovieInfoScreenArgs>()
    @Inject
    lateinit var api: ImdbApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ) = setContent {
        ViewModel(factory = { MovieInfoVM(api) }) { viewModel ->
            val effect = viewModel.uiEffect.collectAsState(initial = null)
            vm.getMovieInfo(args.movieId)
            MovieInfoView(movie = vm.data) {
                viewModel.handleAction(it)
            }

            when (effect.value) {
                is OpenMoviesScreen -> findNavController().popBackStack()
                else -> Unit
            }
        }
    }
}
