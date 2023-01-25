package com.sideki.imdb_app.ui.registration

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sideki.imdb_app.R
import com.sideki.imdb_app.databinding.FragmentRegistrationBinding
import com.sideki.imdb_app.ui.movie_info.MovieInfoVM

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val vm by viewModels<RegistrationVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegistrationBinding.bind(view)
        val loginInput = binding.loginInput.text.toString()
        val passwordInput = binding.passwordInput.text.toString()
        vm.loginError.observe(viewLifecycleOwner) {
            binding.loginInput.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    TODO("Not yet implemented")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    TODO("Not yet implemented")
                }

                override fun afterTextChanged(s: Editable?) {
                    vm.loginError.observe(viewLifecycleOwner){
                        binding.loginField.helperText = vm.loginValidation(s).toString()
                    }
                }

            })
            vm.passwordError.observe(viewLifecycleOwner) {
                binding.passwordField.helperText = vm.passwordValidation(passwordInput).toString()
            }

        }


    }
}
