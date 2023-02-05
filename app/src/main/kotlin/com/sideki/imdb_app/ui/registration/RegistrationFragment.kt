package com.sideki.imdb_app.ui.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sideki.imdb_app.R
import com.sideki.imdb_app.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val vm by viewModels<RegistrationVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegistrationBinding.bind(view)
        binding.loginInput.doAfterTextChanged {
            vm.loginValidation(it.toString())
        }
        vm.loginError.observe(viewLifecycleOwner) {
            binding.loginField.error = it
        }
        binding.nameInput.doAfterTextChanged {
            vm.nameValidation(it.toString())
        }
        vm.nameError.observe(viewLifecycleOwner) {
            binding.nameField.error = it
        }
        binding.passwordInput.doAfterTextChanged {
            vm.passwordValidation(it.toString())
        }
        vm.passwordError.observe(viewLifecycleOwner) {
            binding.passwordField.error = it
        }
        binding.repeatPasswordInput.doAfterTextChanged {
            vm.repeatPasswordValidation(it.toString())
        }
        vm.repeatPasswordError.observe(viewLifecycleOwner) {
            binding.repeatPasswordField.error = it
        }
        vm.isButtonEnabled.observe(viewLifecycleOwner) {
            binding.createAccount.isEnabled = it
        }
        binding.createAccount.setOnClickListener {
            vm.createAccount()
            vm.isAccountCreated.observe(viewLifecycleOwner) {
                if (it == true) findNavController().popBackStack()
            }
        }
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
