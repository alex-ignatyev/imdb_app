package com.sideki.imdb_app.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sideki.imdb_app.R
import com.sideki.imdb_app.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLoginBinding.bind(view)
        binding.logIn.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.toMoviesFragment())
        }
        binding.signUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.toRegistrationFragment())
        }
    }
}
