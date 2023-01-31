package com.sideki.imdb_app.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationVM @Inject constructor() : ViewModel() {

    val isButtonEnabled = MutableLiveData<Boolean>()
    val loginError = MutableLiveData<String?>()
    val nameError = MutableLiveData<String?>()
    val passwordError = MutableLiveData<String?>()
    val repeatPasswordError = MutableLiveData<String?>()

    fun loginValidation(input: String) {
        loginError.value = if (input.length < 8) "Minimum 8 character" else null
    }

    fun nameValidation(input: String) {
        nameError.value = if (input.isEmpty()) "The field must be filled in" else null
    }

    fun passwordValidation(input: String) {
        passwordError.value = if (input.length < 8) "Minimum 8 character" else null
    }

    fun repeatPasswordValidation(input: String) {
        repeatPasswordError.value = if (input.length < 8) "Minimum 8 character" else null
    }

    fun disableButton(input: String, input2: String, input3: String, input4: String) {
        isButtonEnabled.value =
            !(input.isNotBlank() && input2.isNotBlank() && input3.isNotBlank() && input4.isNotBlank())
    }
}
