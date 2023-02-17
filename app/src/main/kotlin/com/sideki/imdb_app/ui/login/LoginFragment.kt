package com.sideki.imdb_app.ui.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sideki.imdb_app.R
import com.sideki.imdb_app.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val vm by viewModels<LoginVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLoginBinding.bind(view)
        binding.loginInput.doAfterTextChanged {
            vm.loginValidation(it.toString())
        }
        binding.passwordInput.doAfterTextChanged {
            vm.passwordValidation(it.toString())
        }
        binding.logIn.setOnClickListener {
            vm.logIn()
        }
        binding.signUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.toRegistrationFragment())
        }
    }
}
