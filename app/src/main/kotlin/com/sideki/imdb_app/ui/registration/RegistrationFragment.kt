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
        passwordEqualityCheck()
        return binding.root
    }

    private fun passwordEqualityCheck(){
        if (binding.passwordInput.text != binding.repeatPasswordInput.text) {
            binding.passwordField.helperText = "пароли не совпадают"
            binding.repeatPasswordField.helperText = "пароли не совпадают"
        } else {
            binding.passwordField.helperText = ""
            binding.repeatPasswordField.helperText = ""
        }
    }
}
