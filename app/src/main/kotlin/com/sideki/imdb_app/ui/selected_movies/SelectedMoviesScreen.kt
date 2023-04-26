package com.sideki.imdb_app.ui.selected_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sideki.imdb_app.domain.use_case.GetSelectedMoviesUseCase
import com.sideki.imdb_app.ui.change_password.ChangePasswordEffect.OpenProfileScreen
import com.sideki.imdb_app.util.base.ViewModel
import com.sideki.imdb_app.util.setContent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SelectedMoviesScreen : Fragment() {

    private val vm by viewModels<SelectedMoviesVM>()

    @Inject
    lateinit var getSelectedMoviesUseCase: GetSelectedMoviesUseCase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ) = setContent {
        ViewModel(factory = { SelectedMoviesVM(getSelectedMoviesUseCase) }) { viewModel ->
            val effect = viewModel.uiEffect.collectAsState(initial = null)
            vm.getSelectedMovies()
            SelectedMoviesView(movies = vm.data) {
                viewModel.handleAction(it)
            }

            when (effect.value) {
                is OpenProfileScreen -> findNavController().popBackStack()
                else -> Unit
            }
        }
    }
}