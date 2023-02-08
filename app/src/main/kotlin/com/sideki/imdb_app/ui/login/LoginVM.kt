package com.sideki.imdb_app.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.domain.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginVM @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    val state = MutableStateFlow(LogInState())
    val isFilledCorrectly = MutableLiveData<Boolean>()

    fun loginValidation(input: String) {
        state.value = state.value.copy(login = input)
    }

    fun passwordValidation(input: String) {
        state.value = state.value.copy(login = input)
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
                        isFilledCorrectly.value = true
                    } else {
                        state.value = copy(passwordError = "Invalid password")
                    }
                } else {
                    state.value = copy(loginError = "Account with this name does not exist")
                }
            }
        }
    }
}

data class LogInState(
    val login: String = "",
    val password: String = "",
    val loginError: String? = null,
    val passwordError: String? = null
)
