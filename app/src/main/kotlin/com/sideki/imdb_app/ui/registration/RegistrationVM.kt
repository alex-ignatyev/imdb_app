package com.sideki.imdb_app.ui.registration

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationVM @Inject constructor() : ViewModel() {

    val loginError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()

    fun loginValidation(input: Editable?) {
        if (input!!.length < 8) loginError.value = "Minimum 8 Character Login"
    }

    fun passwordValidation(input: String) {
        if (input.length < 8) passwordError.value = "Minimum 8 Character Password"
        if (input.matches(".*[@#\$%^&+=].*".toRegex())) passwordError.value =
            "Must Contain 1 Special Character (@#\$%^&+=)"
    }
}
