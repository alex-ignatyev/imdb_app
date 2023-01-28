package com.sideki.imdb_app.ui.registration

import android.text.Editable
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationVM @Inject constructor() : ViewModel() {

    val loginError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()

    fun loginValidation(input: String): String {
        var helperText = "Required"
        if (input.length < 8) helperText = "Minimum 8 Character Login"
        if (input.isNotEmpty() && input.length >= 8) helperText = ""
        return helperText
    }

    fun passwordValidation(input: String): String {
        var helperText = "Required"
        if (input.length < 8) helperText = "Minimum 8 Character Password"
        if (input.length >= 8 && !input.matches(".*[@#\$%^&+=].*".toRegex())) helperText =
            "Must Contain 1 Special Character (@#\$%^&+=)"
        if (input.length >= 8 && input.matches(".*[@#\$%^&+=].*".toRegex())) helperText = ""
        return helperText
    }

    fun passwordEqualityCheck(input: View, input2: View): String {
        var helperText = ""
        if (input != input2) helperText = "Пароли не совпадают"
        return helperText
    }
}
