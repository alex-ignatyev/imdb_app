package com.sideki.imdb_app.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sideki.imdb_app.R
import com.sideki.imdb_app.db.DataStorePreferenceStorage
import com.sideki.imdb_app.ui.profile.ProfileEffect.OpenChangePasswordScreen
import com.sideki.imdb_app.ui.profile.ProfileEffect.OpenLoginScreen
import com.sideki.imdb_app.util.base.ViewModel
import com.sideki.imdb_app.util.setContent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileScreen : Fragment(R.layout.fragment_profile) {

    @Inject
    lateinit var preferences: DataStorePreferenceStorage

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ) = setContent {
        ViewModel(factory = { ProfileVM(preferences) }) { viewModel ->
            val effect = viewModel.uiEffect.collectAsState(initial = null)

            ProfileView {
                viewModel.handleAction(it)
            }

            when (effect.value) {
                is OpenChangePasswordScreen -> findNavController().navigate(ProfileScreenDirections.toChangePasswordFragment())
                is OpenLoginScreen -> findNavController().navigate(ProfileScreenDirections.toLoginFragment())
                else -> Unit
            }
        }
    }
}
