package com.sideki.imdb_app.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sideki.imdb_app.R
import com.sideki.imdb_app.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.signUp.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragment2ToRegistrationFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }
}
