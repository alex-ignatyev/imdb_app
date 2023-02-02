package com.sideki.imdb_app.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class RegistrationVM @Inject constructor() : ViewModel() {

    val isButtonEnabled = MutableLiveData<Boolean>()
    val login = MutableStateFlow("")
    val loginError = MutableLiveData<String?>()
    val name = MutableStateFlow("")
    val nameError = MutableLiveData<String?>()
    val password = MutableStateFlow("")
    val passwordError = MutableLiveData<String?>()
    val repeatPassword = MutableStateFlow("")
    val repeatPasswordError = MutableLiveData<String?>()

    fun loginValidation(input: String) {
        login.value = input
        disableButton()
        loginError.value = if (input.length < 8) "Minimum 8 character" else null
    }

    fun nameValidation(input: String) {
        name.value = input
        disableButton()
        nameError.value = if (input.isEmpty()) "The field must be filled in" else null
    }

    fun passwordValidation(input: String) {
        password.value = input
        disableButton()
        passwordError.value = if (input.length < 8) "Minimum 8 character" else null
    }

    fun repeatPasswordValidation(input: String) {
        repeatPassword.value = input
        disableButton()
        repeatPasswordError.value = if (input.length < 8) "Minimum 8 character" else null
    }

    private fun disableButton() {
        isButtonEnabled.value =
            login.value.trim().isNotEmpty() && name.value.trim().isNotEmpty() && password.value.trim()
                .isNotEmpty() && repeatPassword.value.trim().isNotEmpty() && password.value == repeatPassword.value
    }
}
