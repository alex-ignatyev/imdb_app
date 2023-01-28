package com.sideki.imdb_app.ui.registration

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sideki.imdb_app.R
import com.sideki.imdb_app.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val vm by viewModels<RegistrationVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegistrationBinding.bind(view)
        binding.loginInput.doAfterTextChanged {
            binding.loginField.helperText = vm.loginValidation(it.toString())
        }

        binding.nameInput.doAfterTextChanged {
            binding.nameField.helperText = vm.nameValidation(it.toString())
        }

        binding.passwordInput.doAfterTextChanged {
            binding.passwordField.helperText = vm.passwordValidation(it.toString())

        }
        binding.repeatPasswordInput.doAfterTextChanged {
            binding.repeatPasswordField.helperText = vm.passwordValidation(it.toString())
        }
    }
}
