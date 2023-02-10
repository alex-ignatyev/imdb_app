package com.sideki.imdb_app.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.domain.AccountRepository
import com.sideki.imdb_app.domain.use_case.GetAccountUseCase
import com.sideki.imdb_app.domain.use_case.InsertAccountUseCase
import com.sideki.imdb_app.model.entity.AccountEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.Random.Default
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class RegistrationVM @Inject constructor(
    private val accountRepository: AccountRepository,
    private val insertAccountUseCase: InsertAccountUseCase,
    private val getAccountUseCase: GetAccountUseCase
) : ViewModel() {

    val state = MutableStateFlow(RegistrationState())

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
            val userAccount = getAccountUseCase.getAccount(state.value.login)
            if (userAccount != null) {
                state.value = state.value.copy(loginError = "Account already exist")
            } else {
               insertAccountUseCase.insertAccount(AccountEntity(Random.nextInt()))
                state.value = state.value.copy(isAbleToCreateAccount = true)
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
    var passwordMismatchError: String? = null,
    val isAbleToCreateAccount: Boolean = false
)
