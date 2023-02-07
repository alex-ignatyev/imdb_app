package com.sideki.imdb_app.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.domain.AccountRepository
import com.sideki.imdb_app.model.entity.AccountEntity
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
    val isAbleToCreateAccount = MutableLiveData<Boolean>()

    fun loginValidation(input: String) {
        state.value =
            state.value.copy(login = input, loginError = if (input.length < 8) "Minimum 8 character" else null)
    }

    fun passwordValidation(input: String) {
        with(state.value) {
            if (input.length < 8) state.value = copy(password = input, passwordError = "Minimum 8 character")
            else if (password != repeatPassword) state.value =
                copy(passwordError = "Password mismatch", repeatPasswordError = "Password mismatch")
            else state.value = copy(passwordError = null, repeatPasswordError = null)
        }
    }

    fun repeatPasswordValidation(input: String) {
        with(state.value) {
            if (input.length < 8) state.value =
                copy(repeatPassword = input, repeatPasswordError = "Minimum 8 character")
            else if (password != repeatPassword) state.value =
                copy(passwordError = "Password mismatch", repeatPasswordError = "Password mismatch")
            else state.value = copy(passwordError = null, repeatPasswordError = null)
        }
    }

    fun disableButton(): Boolean {
        return with(state.value) {
            login.trim().isNotEmpty() && password.trim().isNotEmpty() && repeatPassword.trim()
                .isNotEmpty() && password == repeatPassword
        }
    }

    fun createAccount() {
        viewModelScope.launch {
            val userAccount = accountRepository.getAccount(state.value.login)
            if (userAccount != null) {
                state.value = state.value.copy(loginError = "Account already exist")
            } else {
                withContext(Dispatchers.IO) {
                    accountRepository.insertAccount(AccountEntity(0, state.value.login, state.value.password))
                }
                isAbleToCreateAccount.value = true
            }
        }
    }
}

data class RegistrationState(
    val login: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    var loginError: String? = null,
    var passwordError: String? = null,
    var repeatPasswordError: String? = null,
    var passwordMismatchError: String? = null
)
