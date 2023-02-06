package com.sideki.imdb_app.ui.registration

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class RegistrationVM @Inject constructor() : ViewModel() {

    val state = MutableStateFlow(RegistrationState())

    fun loginValidation(input: String) {
        state.value =
            state.value.copy(login = input, loginError = showError(input))
    }

    fun passwordValidation(input: String) {
        state.value =
            state.value.copy(password = input, passwordError = showError(input))
    }

    fun repeatPasswordValidation(input: String) {
        state.value =
            state.value.copy(repeatPassword = input, repeatPasswordError = showError(input))
    }

    fun disableButton(): Boolean {
        return with(state.value) {
            login.trim().isNotEmpty() && password.trim().isNotEmpty() && repeatPassword.trim()
                .isNotEmpty() && password == repeatPassword
        }
    }

    private fun showError(input: String) = if (input.length < 8) "Minimum 8 character" else null
}

data class RegistrationState(
    val login: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val loginError: String? = null,
    val passwordError: String? = null,
    val repeatPasswordError: String? = null
)
