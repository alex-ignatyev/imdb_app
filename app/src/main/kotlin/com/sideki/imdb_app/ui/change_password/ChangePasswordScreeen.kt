package com.sideki.imdb_app.ui.change_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sideki.imdb_app.ui.change_password.ChangePasswordEffect.OpenProfileScreen
import com.sideki.imdb_app.util.base.ViewModel
import com.sideki.imdb_app.util.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?,
    ) = setContent {
        ViewModel(factory = { ChangePasswordVM() }) { viewModel ->
            val state = viewModel.uiState.collectAsState()
            val effect = viewModel.uiEffect.collectAsState(initial = null)

            ChangePasswordView(state = state.value) {
                viewModel.handleAction(it)
            }

            when (effect.value) {
                is OpenProfileScreen -> findNavController().popBackStack()
                else -> Unit
            }
        }
    }
}
