package com.sideki.imdb_app.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.db.DataStorePreferenceStorage
import com.sideki.imdb_app.domain.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginVM @Inject constructor(
    private val accountRepository: AccountRepository,
    private val preferences: DataStorePreferenceStorage
) : ViewModel() {

    val state = MutableStateFlow(LogInState())

    fun loginValidation(input: String) {
        state.value =
            state.value.copy(login = input, loginError = null)
    }

    fun passwordValidation(input: String) {
        state.value = state.value.copy(
            password = input,
            passwordError = null
        )
    }

    fun disableButton(): Boolean {
        return with(state.value) {
            login.trim().isNotEmpty() && password.trim().isNotEmpty()
        }
    }

    fun logIn() {
        viewModelScope.launch {
            with(state.value) {
                val userAccount = accountRepository.getAccount(login)
                if (userAccount != null) {
                    state.value = copy(loginError = null)
                    if (userAccount.password == password) {
                        state.value = copy(hasCorrectFields = true)
                        preferences.saveLoggedInState(true)
                    } else {
                        state.value =
                            copy(
                                passwordError = if (state.value.password.isEmpty()) "The field must be filled in"
                                else "Invalid password"
                            )
                    }
                } else {
                    state.value =
                        copy(
                            loginError = if (state.value.login.isEmpty()) "The field must be filled in"
                            else "Account with this name does not exist"
                        )
                }
            }
        }
    }
}

data class LogInState(
    val login: String = "",
    val password: String = "",
    val loginError: String? = null,
    val passwordError: String? = null,
    val hasCorrectFields: Boolean = false
)
