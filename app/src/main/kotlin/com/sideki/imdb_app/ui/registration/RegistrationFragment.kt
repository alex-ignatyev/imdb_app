package com.sideki.imdb_app.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sideki.imdb_app.R
import com.sideki.imdb_app.databinding.FragmentLoginBinding
import com.sideki.imdb_app.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment(R.layout.fragment_registration) {
    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        validLogin()
        validPassword()
        return binding.root
    }

    private fun validLogin() {
        val loginText = binding.loginInput.text.toString()
        if (loginText.length < 8) binding.loginField.helperText = "Minimum 8 Character Login"
    }

    private fun validPassword() {
        val passwordText = binding.passwordInput.text.toString()
        val repeatPasswordText = binding.repeatPasswordInput.text.toString()
        if (passwordText.length < 8 || repeatPasswordText.length < 8) {
            binding.passwordField.helperText = "Minimum 8 Character Password"
            binding.repeatPasswordField.helperText = "Minimum 8 Character Password"
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex()) || !repeatPasswordText.matches(".*[A-Z].*".toRegex())) {
            binding.passwordField.helperText = "Must Contain 1 Upper-case Character"
            binding.repeatPasswordField.helperText = "Must Contain 1 Upper-case Character"
        }
        if (!passwordText.matches(".*[a-z].*".toRegex()) || !repeatPasswordText.matches(".*[a-z].*".toRegex())) {
            binding.passwordField.helperText = "Must Contain 1 Lower-case Character"
            binding.repeatPasswordField.helperText = "Must Contain 1 Lower-case Character"
        }
        if (!passwordText.matches(".*[@#\$%^&+=].*".toRegex()) || !repeatPasswordText.matches(".*[@#\$%^&+=].*".toRegex())) {
            binding.passwordField.helperText = "Must Contain 1 Special Character (@#\$%^&+=)"
            binding.repeatPasswordField.helperText = "Must Contain 1 Special Character (@#\$%^&+=)"
        }
        if (passwordText != repeatPasswordText) {
            binding.passwordField.helperText = "Passwords do not match"
            binding.repeatPasswordField.helperText = "Passwords do not match"
        }
    }
}
