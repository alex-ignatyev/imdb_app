package com.sideki.imdb_app.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.db.entity.AccountEntity
import com.sideki.imdb_app.domain.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class RegistrationVM @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

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
    private fun passwordEqualityCheck() {
        if (password.value != repeatPassword.value) {
            passwordError.value = "Password mismatch"
            repeatPasswordError.value = "Password mismatch"
        } else {
            passwordError.value = null
            repeatPasswordError.value = null
        }
    }

    private fun disableButton() {
        isButtonEnabled.value =
            login.value.trim().isNotEmpty() && name.value.trim().isNotEmpty() && password.value.trim()
                .isNotEmpty() && repeatPassword.value.trim().isNotEmpty() && password.value == repeatPassword.value
    }

    private fun showError(input: String) = if (input.length < 8) "Minimum 8 character" else null

    fun createAccount() {
        viewModelScope.launch {
            val userAccount = accountRepository.getAccount(login.value)
            if (userAccount != null) {
                loginError.value = "Account already exist"
            } else {
                withContext(Dispatchers.IO) {
                    accountRepository.insertAccount(AccountEntity(0, name.value, login.value, password.value))
                }
                isAccountCreated.value = true
            }
        }
    }
}

data class RegistrationState(
    val login: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val loginError: String? = null,
    val passwordError: String? = null,
    val repeatPasswordError: String? = null
)
