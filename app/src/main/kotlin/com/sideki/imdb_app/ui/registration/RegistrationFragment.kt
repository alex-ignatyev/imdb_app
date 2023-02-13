package com.sideki.imdb_app.ui.registration

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
            vm.obtainLoginChanges(it.toString())
        }
        binding.passwordInput.doAfterTextChanged {
            vm.obtainPasswordChanges(it.toString())
        }
        binding.repeatPasswordInput.doAfterTextChanged {
            vm.obtainRepeatPasswordChanges(it.toString())
        }
        lifecycleScope.launchWhenStarted {
            vm.state.collect {
                binding.createAccount.isEnabled = vm.disableButton()
                binding.loginField.error = it.loginError
                binding.passwordField.error = it.passwordError
                binding.repeatPasswordField.error = it.repeatPasswordError
            }
        }
        binding.createAccount.setOnClickListener {
            vm.createAccount()
            if (vm.state.value.isAbleToCreateAccount) findNavController().popBackStack()

        }
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
