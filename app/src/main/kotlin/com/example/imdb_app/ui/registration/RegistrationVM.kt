package com.example.imdb_app.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb_app.domain.model.AccountModel
import com.example.imdb_app.domain.use_case.GetAccountUseCase
import com.example.imdb_app.domain.use_case.InsertAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class RegistrationVM @Inject constructor(
    private val insertAccountUseCase: InsertAccountUseCase,
    private val getAccountUseCase: GetAccountUseCase
) : ViewModel() {

    val state = MutableStateFlow(RegistrationState())

    fun obtainLoginChanges(input: String) {
        state.value =
            state.value.copy(login = input, loginError = if (input.length < 8) "Minimum 8 character" else null)
    }

    fun obtainPasswordChanges(input: String) {
        with(state.value) {
            state.value = copy(
                password = input,
                passwordError = if (input.length < 8) "Minimum 8 character"
                else if (input != repeatPassword) "Password mismatch" else null,
                repeatPasswordError = if (input != repeatPassword) "Password mismatch" else null
            )
        }
    }

    fun obtainRepeatPasswordChanges(input: String) {
        with(state.value) {
            state.value = copy(
                repeatPassword = input,
                repeatPasswordError = if (input.length < 8) "Minimum 8 character" else if (input != password)
                    "Password mismatch" else null,
                passwordError = if (input != password) "Password mismatch" else null
            )
        }
    }

    fun disableButton(): Boolean {
        return with(state.value) {
            login.trim().isNotEmpty() && password.trim().isNotEmpty() && repeatPassword.trim()
                .isNotEmpty() && password == repeatPassword
        }
    }

    fun createAccount(userAdded: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val userAccount = getAccountUseCase.getAccount(state.value.login)
            if (userAccount != null) {
                state.value = state.value.copy(loginError = "Account already exist")
            } else {
                insertAccountUseCase.insertAccount(
                    AccountModel(
                        login = state.value.login,
                        password = state.value.password
                    )
                )
                withContext(Dispatchers.Main) { userAdded.invoke() }
            }
        }
    }
}

data class RegistrationState(
    val login: String = "",
    var loginError: String? = null,
    val password: String = "",
    var passwordError: String? = null,
    val repeatPassword: String = "",
    var repeatPasswordError: String? = null,
)
