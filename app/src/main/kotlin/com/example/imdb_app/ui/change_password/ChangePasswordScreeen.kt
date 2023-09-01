package com.example.imdb_app.ui.change_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.imdb_app.db.DataStorePreferenceStorage
import com.example.imdb_app.domain.use_case.ChangePasswordUseCase
import com.example.imdb_app.domain.use_case.GetAccountUseCase
import com.example.imdb_app.ui.change_password.ChangePasswordEffect.OpenProfileScreen
import com.example.imdb_app.util.base.ViewModel
import com.example.imdb_app.util.setContent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChangePasswordScreen : Fragment() {

    @Inject
    lateinit var preferences: DataStorePreferenceStorage
    @Inject
    lateinit var changePasswordUseCase: ChangePasswordUseCase
    @Inject
    lateinit var getAccountUseCase: GetAccountUseCase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?,
    ) = setContent {
        ViewModel(factory = { ChangePasswordVM(preferences, changePasswordUseCase, getAccountUseCase) }) { viewModel ->
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
